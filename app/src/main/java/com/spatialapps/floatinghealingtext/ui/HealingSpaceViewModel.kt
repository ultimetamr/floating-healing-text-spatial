package com.spatialapps.floatinghealingtext.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pico.spatial.core.math.EulerAngles
import com.pico.spatial.core.math.Vector3
import com.pico.spatial.sense.world.WorldTrackingManager
import com.pico.spatial.sense.world.WorldTrackingResult
import com.spatialapps.floatinghealingtext.domain.model.HealingColor
import com.spatialapps.floatinghealingtext.domain.model.HealingFont
import com.spatialapps.floatinghealingtext.domain.model.HealingText
import com.spatialapps.floatinghealingtext.domain.usecase.ObserveHealingTextsUseCase
import com.spatialapps.floatinghealingtext.domain.usecase.SaveHealingTextUseCase
import com.spatialapps.floatinghealingtext.platform.SpatialApplication
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HealingSpaceViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as SpatialApplication).healingTextRepository
    private val observeHealingTexts = ObserveHealingTextsUseCase(repository)
    private val saveHealingText = SaveHealingTextUseCase(repository)
    private val preferences = application.getSharedPreferences("healing_space", 0)
    private val _uiState = MutableStateFlow(
        HealingSpaceUiState(reduceMotion = preferences.getBoolean("reduce_motion", false)),
    )
    val uiState: StateFlow<HealingSpaceUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedIfEmpty()
            observeHealingTexts().collect { phrases ->
                _uiState.update { current ->
                    val selectedId = current.selectedId?.takeIf { id -> phrases.any { it.id == id } }
                        ?: phrases.firstOrNull()?.id
                    current.copy(phrases = phrases, selectedId = selectedId)
                }
            }
        }
    }

    fun select(id: Long) = _uiState.update { it.copy(selectedId = id, errorMessage = null) }

    fun beginCreate(initialContent: String = "") = _uiState.update {
        it.copy(
            editorOpen = true,
            draft = HealingText(content = initialContent),
            statusMessage = "正在创建 · 非锚定预览",
            errorMessage = null,
        )
    }

    fun beginEdit(text: HealingText) = _uiState.update {
        it.copy(editorOpen = true, draft = text, selectedId = text.id, errorMessage = null)
    }

    fun closeEditor() = _uiState.update {
        it.copy(editorOpen = false, statusMessage = "Shared Space · 非真实锚定预览")
    }

    fun updateDraftContent(value: String) = _uiState.update { state ->
        state.copy(draft = state.draft.copy(content = value))
    }

    fun updateDraftFont(value: HealingFont) = _uiState.update { state ->
        state.copy(draft = state.draft.copy(font = value))
    }

    fun updateDraftColor(value: HealingColor) = _uiState.update { state ->
        state.copy(draft = state.draft.copy(color = value))
    }

    fun updateDraftSize(value: Float) = _uiState.update { state ->
        state.copy(draft = state.draft.copy(sizeScale = value.coerceIn(0.6f, 1.6f)))
    }

    fun updateDraftOpacity(value: Float) = _uiState.update { state ->
        state.copy(draft = state.draft.copy(opacity = value.coerceIn(0.35f, 1f)))
    }

    fun saveDraft() {
        val draft = _uiState.value.draft.copy(content = _uiState.value.draft.content.trim())
        if (draft.content.isBlank()) {
            _uiState.update { it.copy(errorMessage = "请输入一句想对自己说的话") }
            return
        }
        viewModelScope.launch {
            val id = saveHealingText(draft)
            _uiState.update {
                it.copy(
                    editorOpen = false,
                    selectedId = if (draft.id == 0L) id else draft.id,
                    statusMessage = "已保存到 Room · Shared 中仅显示非锚定预览",
                    errorMessage = null,
                )
            }
        }
    }

    fun deleteLocal(text: HealingText) {
        if (text.anchorUuid != null) {
            _uiState.update {
                it.copy(errorMessage = "这段文字仍有真实锚点，请进入房间模式完成 Anchor + Room 双侧删除")
            }
            return
        }
        viewModelScope.launch {
            repository.delete(text)
            _uiState.update { it.copy(statusMessage = "文字已从 Room 删除") }
        }
    }

    fun transform(text: HealingText, zoom: Float, dxMeters: Float, dyMeters: Float, rollDelta: Float) {
        viewModelScope.launch {
            repository.upsert(
                text.copy(
                    sizeScale = (text.sizeScale * zoom).coerceIn(0.6f, 1.6f),
                    offsetX = (text.offsetX + dxMeters).coerceIn(-1.2f, 1.2f),
                    offsetY = (text.offsetY + dyMeters).coerceIn(-1f, 1f),
                    initialRoll = (text.initialRoll + rollDelta).coerceIn(-45f, 45f),
                ),
            )
        }
    }

    fun setReduceMotion(enabled: Boolean) {
        preferences.edit().putBoolean("reduce_motion", enabled).apply()
        _uiState.update {
            it.copy(
                reduceMotion = enabled,
                statusMessage = if (enabled) "已减少动态 · 浮动与缩放关闭" else "动态效果已开启",
            )
        }
    }

    fun setSpeechStatus(message: String, error: String? = null) = _uiState.update {
        it.copy(statusMessage = message, errorMessage = error)
    }

    fun appendSpeechResult(text: String) = _uiState.update { state ->
        state.copy(
            draft = state.draft.copy(content = text),
            statusMessage = "语音结果待确认",
            errorMessage = null,
        )
    }

    fun locateAnchorsInStage() {
        val uuids = _uiState.value.phrases.mapNotNull { it.anchorUuid }
            .mapNotNull { runCatching { UUID.fromString(it) }.getOrNull() }
        if (uuids.isEmpty()) {
            _uiState.update { it.copy(statusMessage = "房间模式 · 还没有真实锚点") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(anchorBusy = true, statusMessage = "正在定位 ${uuids.size} 个锚点…") }
            when (val result = WorldTrackingManager.loadAnchor(uuids.toTypedArray())) {
                is WorldTrackingResult.Success<*> -> _uiState.update {
                    it.copy(anchorBusy = false, statusMessage = "已在 Stage 定位 ${uuids.size} 个锚点", errorMessage = null)
                }
                is WorldTrackingResult.Error -> _uiState.update {
                    it.copy(
                        anchorBusy = false,
                        statusMessage = "锚点需要恢复",
                        errorMessage = "${result.errorCode}: ${result.errorMessage}",
                    )
                }
            }
        }
    }

    fun createAnchorInStage(text: HealingText) {
        viewModelScope.launch {
            _uiState.update { it.copy(anchorBusy = true, statusMessage = "正在创建 Persistent Spatial Anchor…") }
            val position = Vector3(text.offsetX, 1.45f + text.offsetY, -1.35f + text.offsetZ)
            when (
                val result = WorldTrackingManager.createAnchor(
                    position,
                    EulerAngles(0f, 0f, text.initialRoll),
                    "healing-text-${text.id}",
                )
            ) {
                is WorldTrackingResult.Success<*> -> {
                    val anchor = result.data as? com.pico.spatial.sense.world.WorldAnchor
                    repository.updateAnchor(text.id, anchor?.anchorUUID?.toString())
                    _uiState.update {
                        it.copy(
                            anchorBusy = false,
                            statusMessage = "真实锚点已保存 · 仅在 Stage 中显示为已定位",
                            errorMessage = null,
                        )
                    }
                }
                is WorldTrackingResult.Error -> _uiState.update {
                    it.copy(
                        anchorBusy = false,
                        statusMessage = "锚点创建未完成",
                        errorMessage = "${result.errorCode}: ${result.errorMessage}",
                    )
                }
            }
        }
    }

    fun deleteAnchoredInStage(text: HealingText) {
        val uuid = text.anchorUuid?.let { runCatching { UUID.fromString(it) }.getOrNull() }
        if (uuid == null) {
            deleteLocal(text)
            return
        }
        viewModelScope.launch {
            repository.updateDeleteJournal(text.id, "anchor_pending")
            _uiState.update { it.copy(anchorBusy = true, statusMessage = "正在删除 Anchor…") }
            when (val result = WorldTrackingManager.removeAnchor(uuid)) {
                is WorldTrackingResult.Success<*> -> {
                    repository.updateDeleteJournal(text.id, "room_pending")
                    repository.delete(text)
                    _uiState.update {
                        it.copy(anchorBusy = false, statusMessage = "Anchor + Room 已完整删除", errorMessage = null)
                    }
                }
                is WorldTrackingResult.Error -> {
                    val error = "${result.errorCode}: ${result.errorMessage}"
                    repository.updateDeleteJournal(text.id, "anchor_failed", error)
                    _uiState.update {
                        it.copy(anchorBusy = false, statusMessage = "删除未完成 · 已写入修复日志", errorMessage = error)
                    }
                }
            }
        }
    }
}
