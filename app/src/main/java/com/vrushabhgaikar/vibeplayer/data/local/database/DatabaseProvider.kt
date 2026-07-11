package com.vrushabhgaikar.vibeplayer.data.local.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: VibeDatabase? = null

    fun getDatabase(context: Context): VibeDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                VibeDatabase::class.java,
                "vibe_database"
            ).fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            instance
        }
    }
}