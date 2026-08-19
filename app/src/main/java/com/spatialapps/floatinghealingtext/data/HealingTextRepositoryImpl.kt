package com.spatialapps.floatinghealingtext.data

import com.spatialapps.floatinghealingtext.data.local.HealingTextDao
import com.spatialapps.floatinghealingtext.data.local.toDomain
import com.spatialapps.floatinghealingtext.data.local.toEntity
import com.spatialapps.floatinghealingtext.domain.model.HealingColor
import com.spatialapps.floatinghealingtext.domain.model.HealingFont
import com.spatialapps.floatinghealingtext.domain.model.HealingPresets
import com.spatialapps.floatinghealingtext.domain.model.HealingText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HealingTextRepositoryImpl(
    private val dao: HealingTextDao,
) : HealingTextRepository {
    override fun observeAll(): Flow<List<HealingText>> = dao.observeAll().map { rows -> rows.map { it.toDomain() } }

    override suspend fun seedIfEmpty() {
        // One-time safe cleanup for the former 4th/5th built-in samples. The
        // exact original style/pose match ensures user-edited or anchored text
        // remains untouched.
        dao.deleteRetiredBuiltInSamples(
            fourthPreset = "允许一切如其所是",
            fifthPreset = "你的感受值得被看见",
        )
        if (dao.count() != 0) return
        val colors = HealingColor.entries
        val fonts = HealingFont.entries
        val now = System.currentTimeMillis()
        dao.insertAll(
            HealingPresets.take(3).mapIndexed { index, content ->
                HealingText(
                    content = content,
                    color = colors[index % colors.size],
                    font = fonts[index % fonts.size],
                    sizeScale = 0.86f + index * 0.08f,
                    opacity = 0.8f,
                    updatedAt = now - index,
                ).toEntity()
            },
        )
    }

    override suspend fun upsert(text: HealingText): Long = dao.upsert(
        text.copy(updatedAt = System.currentTimeMillis()).toEntity(),
    )

    override suspend fun delete(text: HealingText) = dao.delete(text.toEntity())

    override suspend fun updateAnchor(id: Long, anchorUuid: String?) =
        dao.updateAnchor(id, anchorUuid, System.currentTimeMillis())

    override suspend fun updateDeleteJournal(id: Long, state: String, error: String?) =
        dao.updateDeleteJournal(id, state, error, System.currentTimeMillis())
}
