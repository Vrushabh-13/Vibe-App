package com.vrushabhgaikar.vibeplayer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vrushabhgaikar.vibeplayer.data.local.dao.RecommendationDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.RecommendedSongEntity

@Database(
    entities = [RecommendedSongEntity::class],
    version = 2,
    exportSchema = false
)
abstract class VibeDatabase: RoomDatabase(){
    abstract fun recommendationDao(): RecommendationDao
}