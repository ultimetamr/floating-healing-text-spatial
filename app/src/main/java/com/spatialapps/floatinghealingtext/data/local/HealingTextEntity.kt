package com.spatialapps.floatinghealingtext.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spatialapps.floatinghealingtext.domain.model.HealingColor
import com.spatialapps.floatinghealingtext.domain.model.HealingFont
import com.spatialapps.floatinghealingtext.domain.model.HealingText

@Entity(tableName = "healing_texts")
data class HealingTextEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val content: String,
    val font: String,
    val color: String,
    val sizeScale: Float,
    val opacity: Float,
    val anchorUuid: String?,
    val offsetX: Float,
    val offsetY: Float,
    val offsetZ: Float,
    val initialRoll: Float,
    val deleteState: String,
    val deleteError: String?,
    val updatedAt: Long,
)

fun HealingTextEntity.toDomain() = HealingText(
    id = id,
    content = content,
    font = runCatching { HealingFont.valueOf(font) }.getOrDefault(HealingFont.SANS),
    color = runCatching { HealingColor.valueOf(color) }.getOrDefault(HealingColor.WHITE),
    sizeScale = sizeScale,
    opacity = opacity,
    anchorUuid = anchorUuid,
    offsetX = offsetX,
    offsetY = offsetY,
    offsetZ = offsetZ,
    initialRoll = initialRoll,
    deleteState = deleteState,
    deleteError = deleteError,
    updatedAt = updatedAt,
)

fun HealingText.toEntity() = HealingTextEntity(
    id = id,
    content = content,
    font = font.name,
    color = color.name,
    sizeScale = sizeScale,
    opacity = opacity,
    anchorUuid = anchorUuid,
    offsetX = offsetX,
    offsetY = offsetY,
    offsetZ = offsetZ,
    initialRoll = initialRoll,
    deleteState = deleteState,
    deleteError = deleteError,
    updatedAt = updatedAt,
)
