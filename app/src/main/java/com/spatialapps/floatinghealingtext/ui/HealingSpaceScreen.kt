package com.spatialapps.floatinghealingtext.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pico.spatial.ui.design.Button
import com.pico.spatial.ui.design.Slider
import com.pico.spatial.ui.design.Text
import com.pico.spatial.ui.design.TextArea
import com.pico.spatial.ui.foundation.hover.spatialHoverEffect
import com.pico.spatial.ui.foundation.material.backgroundMaterial
import com.pico.spatial.ui.platform.Material
import com.pico.spatial.ui.platform.ability.UpperLimbRenderMode
import com.pico.spatial.ui.platform.containers.LocalSpatialNavigator
import com.pico.spatial.ui.platform.containers.OpenStageResult
import com.pico.spatial.ui.platform.containers.StageStyle
import com.spatialapps.floatinghealingtext.HEALING_STAGE_ID
import com.spatialapps.floatinghealingtext.content.HealingVolume
import com.spatialapps.floatinghealingtext.domain.model.HealingColor
import com.spatialapps.floatinghealingtext.domain.model.HealingFont
import com.spatialapps.floatinghealingtext.domain.model.HealingPresets
import com.spatialapps.floatinghealingtext.domain.model.HealingText
import com.spatialapps.floatinghealingtext.voice.SpeechInputController
import kotlinx.coroutines.launch

private val Ink = Color(0xFF182127)
private val MutedInk = Color(0xFF56656B)
private val Mist = Color(0xB3EDF3F2)
private val WarmWhite = Color(0xFFF9F7F1)

@Composable
fun HealingSpaceScreen(
    modifier: Modifier = Modifier,
    viewModel: HealingSpaceViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val navigator = LocalSpatialNavigator.current
    val coroutineScope = rememberCoroutineScope()
    val speech = remember(viewModel) {
        SpeechInputController(
            context = context,
            onStatus = viewModel::setSpeechStatus,
            onResult = viewModel::appendSpeechResult,
        )
    }
    DisposableEffect(speech) { onDispose { speech.destroy() } }
    val microphonePermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (granted) speech.start()
        else viewModel.setSpeechStatus("麦克风权限未开启", "仍可使用空间键盘输入")
    }

    fun beginVoice() {
        if (!state.editorOpen) viewModel.beginCreate()
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            speech.start()
        } else {
            microphonePermission.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    HealingVolume(
        modifier = modifier,
        state = state,
        onSelect = viewModel::select,
        onTransform = { text, zoom, dx, dy, rotation -> viewModel.transform(text, zoom, dx, dy, rotation) },
        onDelete = viewModel::deleteLocal,
        dashboard = {
            SharedDashboard(
                state = state,
                onCreate = { viewModel.beginCreate() },
                onVoice = ::beginVoice,
                onEdit = viewModel::beginEdit,
                onSelect = viewModel::select,
                onPreset = { viewModel.beginCreate(it) },
                onToggleMotion = { viewModel.setReduceMotion(!state.reduceMotion) },
                onEnterStage = {
                    coroutineScope.launch {
                        navigator.minimizeWindowContainer()
                        when (val result = navigator.openStage(
                            HEALING_STAGE_ID,
                            StageStyle.Mixed,
                            Bundle(),
                            UpperLimbRenderMode.Visible,
                        )) {
                            OpenStageResult.Allowed -> viewModel.setSpeechStatus("房间锚定模式已打开")
                            OpenStageResult.NotAllowed -> {
                                navigator.restoreWindowContainer()
                                viewModel.setSpeechStatus("系统暂不允许打开 Stage", "请结束其他沉浸式体验后重试")
                            }
                            is OpenStageResult.Error -> {
                                navigator.restoreWindowContainer()
                                viewModel.setSpeechStatus("Stage 打开失败", "${result.code}: ${result.reason}")
                            }
                        }
                    }
                },
                editor = if (state.editorOpen) {
                    {
                        HealingEditor(
                            state = state,
                            onContentChange = viewModel::updateDraftContent,
                            onFont = viewModel::updateDraftFont,
                            onColor = viewModel::updateDraftColor,
                            onSize = viewModel::updateDraftSize,
                            onOpacity = viewModel::updateDraftOpacity,
                            onVoice = ::beginVoice,
                            onSave = viewModel::saveDraft,
                            onClose = viewModel::closeEditor,
                        )
                    }
                } else null,
            )
        },
    )
}

@Composable
private fun SharedDashboard(
    state: HealingSpaceUiState,
    onCreate: () -> Unit,
    onVoice: () -> Unit,
    onEdit: (HealingText) -> Unit,
    onSelect: (Long) -> Unit,
    onPreset: (String) -> Unit,
    onToggleMotion: () -> Unit,
    onEnterStage: () -> Unit,
    editor: (@Composable () -> Unit)?,
) {
    Box(
        modifier = Modifier
            .size(width = 1_216.dp, height = 768.dp)
            .padding(32.dp),
    ) {
        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text("悬浮文字疗愈空间", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, color = Ink)
                    Text("一字一息 · Shared Space 管理与预览", fontSize = 15.sp, color = MutedInk)
                }
                Button(onClick = onToggleMotion) {
                    Text(if (state.reduceMotion) "开启柔和动态" else "减少动态")
                }
                Spacer(Modifier.width(10.dp))
                Button(onClick = onEnterStage) { Text("进入房间锚定") }
            }
            Spacer(Modifier.height(16.dp))
            StatusStrip(state)
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                LibraryPane(
                    modifier = Modifier.width(390.dp).fillMaxHeight(),
                    state = state,
                    onCreate = onCreate,
                    onVoice = onVoice,
                    onEdit = onEdit,
                    onSelect = onSelect,
                    onPreset = onPreset,
                )
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    Text("空间预览", fontSize = 19.sp, fontWeight = FontWeight.Medium, color = Ink)
                    Text(
                        "凝视会高亮 · 点击选择 · 拖动/双指可移动、缩放与旋转",
                        fontSize = 14.sp,
                        color = MutedInk,
                    )
                    Spacer(Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        state.selected?.let { selected ->
                            Button(onClick = { onEdit(selected) }) { Text("编辑所选") }
                        }
                        Button(onClick = onCreate) { Text("＋ 新建文字") }
                    }
                }
            }
        }
        editor?.invoke()
    }
}

@Composable
private fun StatusStrip(state: HealingSpaceUiState) {
    val shape = RoundedCornerShape(18.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(Mist)
            .border(1.dp, Color.White.copy(alpha = 0.55f), shape)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(9.dp).clip(CircleShape).background(if (state.errorMessage == null) Color(0xFF72A693) else Color(0xFFD68479)))
        Spacer(Modifier.width(9.dp))
        Column {
            Text(state.statusMessage, fontSize = 14.sp, color = Ink)
            state.errorMessage?.let { Text(it, fontSize = 12.sp, color = Color(0xFF9C413B)) }
        }
    }
}

@Composable
private fun LibraryPane(
    modifier: Modifier,
    state: HealingSpaceUiState,
    onCreate: () -> Unit,
    onVoice: () -> Unit,
    onEdit: (HealingText) -> Unit,
    onSelect: (Long) -> Unit,
    onPreset: (String) -> Unit,
) {
    val shape = RoundedCornerShape(24.dp)
    Column(
        modifier = modifier
            .clip(shape)
            .background(Color.White.copy(alpha = 0.58f))
            .border(1.dp, Color.White.copy(alpha = 0.72f), shape)
            .padding(18.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("我的文字 ${state.phrases.size}", fontSize = 19.sp, fontWeight = FontWeight.Medium, color = Ink)
            Spacer(Modifier.weight(1f))
            Button(onClick = onVoice) { Text("语音＋") }
        }
        Spacer(Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.height(250.dp),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.phrases, key = { it.id }) { phrase ->
                PhraseRow(
                    phrase = phrase,
                    selected = state.selectedId == phrase.id,
                    onSelect = { onSelect(phrase.id) },
                    onEdit = { onEdit(phrase) },
                )
            }
        }
        Spacer(Modifier.height(14.dp))
        Text("3 条治愈预设", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Ink)
        Spacer(Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            items(HealingPresets) { preset ->
                Text(
                    text = preset,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .spatialHoverEffect()
                        .clickable { onPreset(preset) }
                        .padding(horizontal = 12.dp, vertical = 9.dp),
                    color = MutedInk,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onCreate, modifier = Modifier.fillMaxWidth()) { Text("自定义一句话") }
    }
}

@Composable
private fun PhraseRow(
    phrase: HealingText,
    selected: Boolean,
    onSelect: () -> Unit,
    onEdit: () -> Unit,
) {
    val shape = RoundedCornerShape(14.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .spatialHoverEffect()
            .clickable(onClick = onSelect)
            .background(if (selected) Color(0x4D98C3B6) else Color.Transparent)
            .border(1.dp, if (selected) Color(0xFF7BA797) else Color(0x1A182127), shape)
            .padding(11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(12.dp).clip(CircleShape).background(Color(phrase.color.argb)))
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(phrase.content, color = Ink, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(
                "${phrase.font.displayName} · ${(phrase.opacity * 100).toInt()}%${if (phrase.anchorUuid != null) " · 已锚定" else ""}",
                color = MutedInk,
                fontSize = 11.sp,
            )
        }
        Button(onClick = onEdit) { Text("编辑") }
    }
}

@Composable
private fun HealingEditor(
    state: HealingSpaceUiState,
    onContentChange: (String) -> Unit,
    onFont: (HealingFont) -> Unit,
    onColor: (HealingColor) -> Unit,
    onSize: (Float) -> Unit,
    onOpacity: (Float) -> Unit,
    onVoice: () -> Unit,
    onSave: () -> Unit,
    onClose: () -> Unit,
) {
    val draft = state.draft
    val shape = RoundedCornerShape(26.dp)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
        Column(
            modifier = Modifier
                .width(550.dp)
                .clip(shape)
                .backgroundMaterial(enable = true, style = Material.Thickest)
                .border(1.dp, Color.White.copy(alpha = 0.76f), shape)
                .padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(if (draft.id == 0L) "创建悬浮文字" else "编辑悬浮文字", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Ink)
                Text("保存后可进入房间模式建立真实锚点", fontSize = 12.sp, color = MutedInk)
            }
            Button(onClick = onClose) { Text("关闭") }
        }
        TextArea(
            value = draft.content,
            onValueChange = { onContentChange(it.take(80)) },
            modifier = Modifier.fillMaxWidth().height(110.dp),
            placeholder = { Text("写下此刻想听见的话…") },
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            HealingFont.entries.forEach { font ->
                Button(onClick = { onFont(font) }, enabled = draft.font != font) { Text(font.displayName) }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            HealingColor.entries.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(if (draft.color == color) 38.dp else 32.dp)
                        .clip(CircleShape)
                        .spatialHoverEffect()
                        .clickable { onColor(color) }
                        .background(Color(color.argb))
                        .border(2.dp, if (draft.color == color) Ink else Color.White.copy(alpha = 0.75f), CircleShape),
                )
            }
        }
        Text("大小 ${(draft.sizeScale * 100).toInt()}%", fontSize = 13.sp, color = MutedInk)
        Slider(draft.sizeScale, onSize, Modifier.fillMaxWidth(), 0.6f..1.6f)
        Text("透明度 ${(draft.opacity * 100).toInt()}%", fontSize = 13.sp, color = MutedInk)
        Slider(draft.opacity, onOpacity, Modifier.fillMaxWidth(), 0.35f..1f)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = onVoice) { Text("语音输入") }
            Spacer(Modifier.weight(1f))
            Button(onClick = onSave) { Text("保存文字") }
        }
        }
    }
}
