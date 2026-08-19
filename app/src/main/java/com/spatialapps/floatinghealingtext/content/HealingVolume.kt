package com.spatialapps.floatinghealingtext.content

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pico.spatial.core.ecs.Entity
import com.pico.spatial.core.ecs.LookAtComponent
import com.pico.spatial.core.ecs.LookAtForwardDirection
import com.pico.spatial.core.ecs.TransformComponent
import com.pico.spatial.core.math.EulerAngles
import com.pico.spatial.core.math.Vector3
import com.pico.spatial.ui.foundation.content.SpatialView
import com.spatialapps.floatinghealingtext.domain.model.HealingText
import com.spatialapps.floatinghealingtext.ui.HealingSpaceUiState
import com.spatialapps.floatinghealingtext.ui.components.HealingTextVisual

private val SharedTopPlacements = listOf(
    Triple(-0.46f, 0.37f, 0.18f),
    Triple(0.00f, 0.37f, 0.18f),
    Triple(0.46f, 0.37f, 0.18f),
)
private const val MaxSharedTextObjects = 3

@Composable
fun HealingVolume(
    modifier: Modifier,
    state: HealingSpaceUiState,
    onSelect: (Long) -> Unit,
    onTransform: (HealingText, Float, Float, Float, Float) -> Unit,
    onDelete: (HealingText) -> Unit,
    dashboard: @Composable () -> Unit,
) {
    val visiblePhrases = state.phrases.take(MaxSharedTextObjects)
    SpatialView(
        modifier = modifier,
        initial = { content, attachments ->
            attachments.entity("shared-dashboard")?.apply {
                setName("shared_dashboard")
                components[TransformComponent::class.java]?.setPosition(Vector3(0f, -0.13f, -0.04f))
                content.addEntity(this)
            }

            repeat(MaxSharedTextObjects) { slot ->
                val root = Entity().apply {
                    setName("healing_root_$slot")
                    components[TransformComponent::class.java] = TransformComponent()
                    components[LookAtComponent::class.java] = LookAtComponent().apply {
                        setViewerAsTarget()
                        alignLocalUpToWorldUp = true
                        lookAtForwardDirection = LookAtForwardDirection.POSITIVE_Z
                    }
                }
                attachments.entity("healing-front-$slot")?.apply {
                    setName("healing_front_$slot")
                    components[TransformComponent::class.java]?.setPosition(Vector3(0f, 0f, 0f))
                    root.addChild(this)
                }
                content.addEntity(root)
            }
        },
        update = { content, _ ->
            repeat(MaxSharedTextObjects) { slot ->
                val phrase = visiblePhrases.getOrNull(slot)
                content.entities.firstOrNull { it.getName() == "healing_root_$slot" }?.apply {
                    enabled = phrase != null
                    phrase?.let {
                        components[TransformComponent::class.java]?.apply {
                            val (baseX, baseY, baseZ) = SharedTopPlacements[slot]
                            setPosition(
                                Vector3(
                                    (baseX + it.offsetX * 0.06f).coerceIn(-0.55f, 0.55f),
                                    (baseY + it.offsetY * 0.02f).coerceIn(0.35f, 0.39f),
                                    baseZ + it.offsetZ * 0.08f,
                                ),
                            )
                            setEulerAngles(EulerAngles(0f, 0f, it.initialRoll))
                            setScaleVector(Vector3(it.sizeScale, it.sizeScale, it.sizeScale))
                        }
                    }
                }
            }
        },
        attachments = {
            AttachmentPanel(id = "shared-dashboard") { dashboard() }
            repeat(MaxSharedTextObjects) { slot ->
                AttachmentPanel(id = "healing-front-$slot") {
                    visiblePhrases.getOrNull(slot)?.let { phrase ->
                        HealingTextVisual(
                            text = phrase,
                            selected = state.selectedId == phrase.id,
                            reduceMotion = state.reduceMotion,
                            onSelect = { onSelect(phrase.id) },
                            onTransform = { zoom, dx, dy, roll -> onTransform(phrase, zoom, dx, dy, roll) },
                            onDelete = { onDelete(phrase) },
                        )
                    } ?: Box(Modifier)
                }
            }
        },
    )
}
