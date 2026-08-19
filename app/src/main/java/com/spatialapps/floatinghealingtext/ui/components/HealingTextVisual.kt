package com.spatialapps.floatinghealingtext.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pico.spatial.ui.design.Button
import com.pico.spatial.ui.design.Text
import com.pico.spatial.ui.foundation.hover.spatialHoverEffect
import com.pico.spatial.ui.foundation.material.backgroundMaterial
import com.pico.spatial.ui.platform.Material
import com.spatialapps.floatinghealingtext.domain.model.HealingFont
import com.spatialapps.floatinghealingtext.domain.model.HealingText
import kotlin.math.PI

internal fun HealingFont.asFontFamily(): FontFamily = when (this) {
    HealingFont.SANS -> FontFamily.SansSerif
    HealingFont.SERIF -> FontFamily.Serif
    HealingFont.HANDWRITTEN -> FontFamily.Cursive
}

@Composable
fun HealingTextVisual(
    text: HealingText,
    selected: Boolean,
    reduceMotion: Boolean,
    backFace: Boolean = false,
    interactive: Boolean = true,
    onSelect: () -> Unit = {},
    onTransform: (zoom: Float, dxMeters: Float, dyMeters: Float, rollDegrees: Float) -> Unit = { _, _, _, _ -> },
    onDelete: () -> Unit = {},
) {
    var showDelete by remember(text.id) { mutableStateOf(false) }
    val transition = rememberInfiniteTransition(label = "healing-text-motion-${text.id}")
    val floatY by transition.animateFloat(
        initialValue = if (reduceMotion) 0f else -10f,
        targetValue = if (reduceMotion) 0f else 10f,
        animationSpec = infiniteRepeatable(tween(4_000), RepeatMode.Reverse),
        label = "float-8s",
    )
    val breathe by transition.animateFloat(
        initialValue = if (reduceMotion) 1f else 0.82f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3_000), RepeatMode.Reverse),
        label = "breathe-6s",
    )
    val transformState = rememberTransformableState { zoom, pan, rotation ->
        if (interactive) {
            onTransform(zoom, pan.x / 1_000f, -pan.y / 1_000f, rotation)
        }
    }
    val shape = RoundedCornerShape(28.dp)
    val phraseColor = Color(text.color.argb).copy(alpha = text.opacity)
    val borderColor = phraseColor.copy(alpha = if (selected) 0.72f else 0.25f)
    val interactionModifier = if (interactive) {
        Modifier
            .spatialHoverEffect()
            .pointerInput(text.id) {
                detectTapGestures(
                    onTap = { onSelect() },
                    onDoubleTap = { onTransform(1.08f, 0f, 0f, 0f) },
                    onLongPress = { showDelete = true },
                )
            }
            .transformable(transformState)
    } else {
        Modifier
    }

    Box(
        modifier = Modifier
            .size(width = 430.dp, height = 160.dp)
            .graphicsLayer {
                translationY = floatY
                scaleX = if (selected && !reduceMotion) 1.025f else 1f
                scaleY = if (selected && !reduceMotion) 1.025f else 1f
                rotationY = 0f
            }
            .clip(shape)
            .then(interactionModifier)
            .backgroundMaterial(enable = true, style = Material.Thin)
            .border(1.5.dp, borderColor, shape)
            .alpha(if (selected) 1f else breathe),
        contentAlignment = Alignment.Center,
    ) {
        // The enclosing ECS entity supplies physical Z depth. Keep a single
        // glyph layer here so the soft glow never reads as duplicate black text.
        Text(
            text = text.content,
            modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp),
            color = phraseColor,
            fontFamily = text.font.asFontFamily(),
            fontSize = (42f * text.sizeScale).sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            style = androidx.compose.ui.text.TextStyle(
                shadow = Shadow(phraseColor.copy(alpha = 0.52f), blurRadius = 14f),
                fontStyle = if (text.font == HealingFont.HANDWRITTEN) FontStyle.Italic else FontStyle.Normal,
            ),
        )
        if (showDelete && interactive) {
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Button(onClick = {
                    showDelete = false
                    onDelete()
                }) {
                    Text("删除")
                }
            }
        }
    }
}
