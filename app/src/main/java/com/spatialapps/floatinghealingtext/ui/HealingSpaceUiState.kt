package com.spatialapps.floatinghealingtext.ui

import com.spatialapps.floatinghealingtext.domain.model.HealingText

data class HealingSpaceUiState(
    val phrases: List<HealingText> = emptyList(),
    val selectedId: Long? = null,
    val editorOpen: Boolean = false,
    val draft: HealingText = HealingText(content = ""),
    val reduceMotion: Boolean = false,
    val anchorBusy: Boolean = false,
    val statusMessage: String = "Shared Space · 非真实锚定预览",
    val errorMessage: String? = null,
) {
    val selected: HealingText?
        get() = phrases.firstOrNull { it.id == selectedId } ?: phrases.firstOrNull()
}

sealed interface HealingSpaceEvent {
    data class Select(val id: Long) : HealingSpaceEvent
    data object Create : HealingSpaceEvent
    data class Edit(val text: HealingText) : HealingSpaceEvent
    data class UsePreset(val content: String) : HealingSpaceEvent
    data object Save : HealingSpaceEvent
    data object CloseEditor : HealingSpaceEvent
    data object EnterAnchorStage : HealingSpaceEvent
    data object ToggleReduceMotion : HealingSpaceEvent
}
