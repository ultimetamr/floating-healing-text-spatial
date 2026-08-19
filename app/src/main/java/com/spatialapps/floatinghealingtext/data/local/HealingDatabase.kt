package com.spatialapps.floatinghealingtext.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HealingTextEntity::class], version = 1, exportSchema = true)
abstract class HealingDatabase : RoomDatabase() {
    abstract fun healingTextDao(): HealingTextDao

    companion object {
        @Volatile private var instance: HealingDatabase? = null

        fun get(context: Context): HealingDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                HealingDatabase::class.java,
                "floating-healing-text.db",
            ).build().also { instance = it }
        }
    }
}
