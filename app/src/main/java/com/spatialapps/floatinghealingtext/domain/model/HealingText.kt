package com.spatialapps.floatinghealingtext.domain.model

enum class HealingFont(val displayName: String) {
    SANS("无衬线"),
    SERIF("衬线"),
    HANDWRITTEN("手写体"),
}

enum class HealingColor(val displayName: String, val argb: Long) {
    WHITE("纯净白", 0xFFFFFFFF),
    WARM_GOLD("暖金黄", 0xFFFFE4B5),
    SOFT_PINK("柔粉色", 0xFFFFB6C1),
    CLEAR_BLUE("清蓝色", 0xFFB0E0E6),
    MINT_GREEN("薄荷绿", 0xFF98FB98),
}

data class HealingText(
    val id: Long = 0,
    val content: String,
    val font: HealingFont = HealingFont.SANS,
    val color: HealingColor = HealingColor.WHITE,
    val sizeScale: Float = 1f,
    val opacity: Float = 0.8f,
    val anchorUuid: String? = null,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val offsetZ: Float = 0f,
    val initialRoll: Float = 0f,
    val deleteState: String = "idle",
    val deleteError: String? = null,
    val updatedAt: Long = System.currentTimeMillis(),
)

val HealingPresets = listOf(
    "你已经做得很好了",
    "慢一点，也是在前进",
    "今天也请温柔地对待自己",
)
