package com.spatialapps.floatinghealingtext.domain.repository

import com.spatialapps.floatinghealingtext.domain.model.HealingText
import kotlinx.coroutines.flow.Flow

interface HealingTextRepository {
    fun observeAll(): Flow<List<HealingText>>

    suspend fun seedIfEmpty()

    suspend fun upsert(text: HealingText): Long

    suspend fun delete(text: HealingText)

    suspend fun updateAnchor(id: Long, anchorUuid: String?)

    suspend fun updateDeleteJournal(id: Long, state: String, error: String? = null)
}
