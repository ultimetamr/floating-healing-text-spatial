package com.spatialapps.floatinghealingtext.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pico.spatial.core.ecs.AnchorEntity
import com.pico.spatial.core.ecs.Entity
import com.pico.spatial.core.ecs.LookAtComponent
import com.pico.spatial.core.ecs.LookAtForwardDirection
import com.pico.spatial.core.ecs.TransformComponent
import com.pico.spatial.core.ecs.anchor.AnchorTarget
import com.pico.spatial.core.math.EulerAngles
import com.pico.spatial.core.math.Vector3
import com.pico.spatial.ui.design.Button
import com.pico.spatial.ui.design.Text
import com.pico.spatial.ui.foundation.content.SpatialView
import com.pico.spatial.ui.platform.containers.LocalSpatialNavigator
import com.spatialapps.floatinghealingtext.domain.model.HealingText
import com.spatialapps.floatinghealingtext.ui.HealingSpaceViewModel
import com.spatialapps.floatinghealingtext.ui.components.HealingTextVisual
import java.util.UUID
import kotlinx.coroutines.launch

private const val MaxStageTextObjects = 8
private val StageInk = Color(0xFF182127)

@Composable
fun HealingAnchorStage(viewModel: HealingSpaceViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val navigator = LocalSpatialNavigator.current
    val scope = rememberCoroutineScope()
    val phrases = state.phrases.take(MaxStageTextObjects)
    val anchorKey = phrases.joinToString("|") { "${it.id}:${it.anchorUuid}" }

    LaunchedEffect(anchorKey) { viewModel.locateAnchorsInStage() }

    key(anchorKey) {
        SpatialView(
            initial = { content, attachments ->
                attachments.entity("anchor-control")?.apply {
                    setName("anchor_control")
                    components[TransformComponent::class.java]?.apply {
                        setPosition(Vector3(-0.62f, 1.40f, -1.10f))
                        setEulerAngles(EulerAngles(0f, 0f, 0f))
                    }
                    components[LookAtComponent::class.java] = LookAtComponent().apply {
                        setViewerAsTarget()
                        alignLocalUpToWorldUp = true
                        lookAtForwardDirection = LookAtForwardDirection.POSITIVE_Z
                    }
                    content.addEntity(this)
                }

                phrases.forEachIndexed { slot, phrase ->
                    val anchorUuid = phrase.anchorUuid?.let { runCatching { UUID.fromString(it) }.getOrNull() }
                    val placementRoot: Entity = if (anchorUuid != null) {
                        AnchorEntity(AnchorTarget.createWorldAnchorTarget(anchorUuid))
                    } else {
                        Entity().apply {
                            components[TransformComponent::class.java] = TransformComponent(
                                Vector3(
                                    -0.12f + (slot % 2) * 0.56f,
                                    1.62f - (slot / 2) * 0.27f,
                                    -1.38f - (slot % 3) * 0.08f,
                                ),
                                EulerAngles(0f, 0f, 0f),
                                Vector3(1f, 1f, 1f),
                            )
                        }
                    }
                    placementRoot.setName("stage_placement_$slot")

                    val facingRoot = Entity().apply {
                        setName("stage_facing_$slot")
                        components[TransformComponent::class.java] = TransformComponent(
                            Vector3(phrase.offsetX, phrase.offsetY, phrase.offsetZ),
                            EulerAngles(0f, 0f, phrase.initialRoll),
                            Vector3(phrase.sizeScale, phrase.sizeScale, phrase.sizeScale),
                        )
                        components[LookAtComponent::class.java] = LookAtComponent().apply {
                            setViewerAsTarget()
                            alignLocalUpToWorldUp = true
                            lookAtForwardDirection = LookAtForwardDirection.POSITIVE_Z
                        }
                    }
                    val shallowBody = Entity.loadSuspend("asset://box.usdz").apply {
                        setName("stage_extrusion_$slot")
                        components[TransformComponent::class.java]?.setScaleVector(Vector3(0.42f, 0.12f, 0.07f))
                    }
                    facingRoot.addChild(shallowBody)
                    attachments.entity("stage-front-$slot")?.apply {
                        components[TransformComponent::class.java]?.setPosition(Vector3(0f, 0f, 0.041f))
                        facingRoot.addChild(this)
                    }
                    attachments.entity("stage-back-$slot")?.apply {
                        components[TransformComponent::class.java]?.apply {
                            setPosition(Vector3(0f, 0f, -0.041f))
                            setEulerAngles(EulerAngles(0f, 180f, 0f))
                        }
                        facingRoot.addChild(this)
                    }
                    placementRoot.addChild(facingRoot)
                    content.addEntity(placementRoot)
                }
            },
            update = { content, _ ->
                phrases.forEachIndexed { slot, phrase ->
                    content.entities.firstOrNull { it.getName() == "stage_placement_$slot" }
                        ?.findEntity("stage_facing_$slot")
                        ?.components
                        ?.get(TransformComponent::class.java)
                        ?.apply {
                            setPosition(Vector3(phrase.offsetX, phrase.offsetY, phrase.offsetZ))
                            setEulerAngles(EulerAngles(0f, 0f, phrase.initialRoll))
                            setScaleVector(Vector3(phrase.sizeScale, phrase.sizeScale, phrase.sizeScale))
                        }
                }
            },
            attachments = {
                AttachmentPanel(id = "anchor-control") {
                    StageControlPanel(
                        phrases = phrases,
                        status = state.statusMessage,
                        error = state.errorMessage,
                        busy = state.anchorBusy,
                        onCreateAnchor = viewModel::createAnchorInStage,
                        onDelete = viewModel::deleteAnchoredInStage,
                        onClose = {
                            scope.launch {
                                navigator.restoreWindowContainer()
                                navigator.closeStage()
                            }
                        },
                    )
                }
                repeat(MaxStageTextObjects) { slot ->
                    AttachmentPanel(id = "stage-front-$slot") {
                        phrases.getOrNull(slot)?.let { phrase ->
                            HealingTextVisual(
                                text = phrase,
                                selected = state.selectedId == phrase.id,
                                reduceMotion = state.reduceMotion,
                                onSelect = { viewModel.select(phrase.id) },
                                onTransform = { zoom, dx, dy, roll -> viewModel.transform(phrase, zoom, dx, dy, roll) },
                                onDelete = { viewModel.deleteAnchoredInStage(phrase) },
                            )
                        } ?: Box(Modifier)
                    }
                    AttachmentPanel(id = "stage-back-$slot") {
                        phrases.getOrNull(slot)?.let { phrase ->
                            HealingTextVisual(
                                text = phrase,
                                selected = state.selectedId == phrase.id,
                                reduceMotion = state.reduceMotion,
                                backFace = true,
                                interactive = false,
                            )
                        } ?: Box(Modifier)
                    }
                }
            },
        )
    }
}

@Composable
private fun StageControlPanel(
    phrases: List<HealingText>,
    status: String,
    error: String?,
    busy: Boolean,
    onCreateAnchor: (HealingText) -> Unit,
    onDelete: (HealingText) -> Unit,
    onClose: () -> Unit,
) {
    val shape = RoundedCornerShape(24.dp)
    Column(
        modifier = Modifier
            .size(width = 520.dp, height = 620.dp)
            .clip(shape)
            .background(StageInk.copy(alpha = 0.96f))
            .border(1.dp, Color.White.copy(alpha = 0.15f), shape)
            .padding(22.dp),
    ) {
        Text("房间锚定模式", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Text("Stage Mixed · Persistent Anchor 的唯一真实生命周期", color = Color(0xFFB9CAC8), fontSize = 13.sp)
        Spacer(Modifier.height(12.dp))
        Text(status, color = Color(0xFFFFE4B5), fontSize = 14.sp)
        error?.let { Text(it, color = Color(0xFFFFB6C1), fontSize = 12.sp) }
        Spacer(Modifier.height(14.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(9.dp),
        ) {
            items(phrases, key = { it.id }) { phrase ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White.copy(alpha = 0.07f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(phrase.content, color = Color.White, fontSize = 14.sp, maxLines = 2)
                        Text(
                            if (phrase.anchorUuid == null) "尚未锚定" else "Persistent Anchor 已绑定",
                            color = if (phrase.anchorUuid == null) Color(0xFFB9CAC8) else Color(0xFF98FB98),
                            fontSize = 11.sp,
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    if (phrase.anchorUuid == null) {
                        Button(onClick = { onCreateAnchor(phrase) }, enabled = !busy) { Text("放置") }
                    } else {
                        Button(onClick = { onDelete(phrase) }, enabled = !busy) { Text("删除") }
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Text("拖动/双指变换会保存相对 Anchor 的偏移；退出后 Shared 仅保留非世界锁定预览。", color = Color(0xFFB9CAC8), fontSize = 12.sp)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onClose, modifier = Modifier.fillMaxWidth()) { Text("完成并返回 Shared Space") }
    }
}
