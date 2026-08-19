package com.spatialapps.floatinghealingtext.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HealingTextDao {
    @Query("SELECT * FROM healing_texts ORDER BY updatedAt DESC")
    fun observeAll(): Flow<List<HealingTextEntity>>

    @Query("SELECT COUNT(*) FROM healing_texts")
    suspend fun count(): Int

    @Query(
        """
        DELETE FROM healing_texts
        WHERE anchorUuid IS NULL
          AND offsetX = 0 AND offsetY = 0 AND offsetZ = 0 AND initialRoll = 0
          AND opacity = 0.8 AND deleteState = 'idle'
          AND (
            (content = :fourthPreset AND font = 'SANS' AND color = 'CLEAR_BLUE' AND sizeScale BETWEEN 1.099 AND 1.101)
            OR
            (content = :fifthPreset AND font = 'SERIF' AND color = 'MINT_GREEN' AND sizeScale BETWEEN 1.179 AND 1.181)
          )
        """,
    )
    suspend fun deleteRetiredBuiltInSamples(
        fourthPreset: String,
        fifthPreset: String,
    ): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: HealingTextEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<HealingTextEntity>)

    @Delete
    suspend fun delete(entity: HealingTextEntity)

    @Query("UPDATE healing_texts SET anchorUuid = :anchorUuid, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateAnchor(id: Long, anchorUuid: String?, updatedAt: Long)

    @Query("UPDATE healing_texts SET deleteState = :state, deleteError = :error, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateDeleteJournal(id: Long, state: String, error: String?, updatedAt: Long)
}
