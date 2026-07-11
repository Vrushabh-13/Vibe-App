package com.vrushabhgaikar.vibeplayer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vrushabhgaikar.vibeplayer.data.local.dao.FavoriteDao
import com.vrushabhgaikar.vibeplayer.data.local.dao.HistoryDao
import com.vrushabhgaikar.vibeplayer.data.local.dao.PlaylistDao
import com.vrushabhgaikar.vibeplayer.data.local.dao.RecommendationDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.FavoriteSongEntity
import com.vrushabhgaikar.vibeplayer.data.local.entity.HistoryEntity
import com.vrushabhgaikar.vibeplayer.data.local.entity.PlaylistEntity
import com.vrushabhgaikar.vibeplayer.data.local.entity.PlaylistSongCrossRef
import com.vrushabhgaikar.vibeplayer.data.local.entity.RecommendedSongEntity

@Database(
    entities = [
        RecommendedSongEntity::class,
        FavoriteSongEntity::class,
        HistoryEntity::class,
        PlaylistEntity::class,
        PlaylistSongCrossRef::class
    ],
    version = 4,
    exportSchema = false
)
abstract class VibeDatabase : RoomDatabase() {

    abstract fun recommendationDao(): RecommendationDao

    abstract fun favoriteDao(): FavoriteDao

    abstract fun historyDao(): HistoryDao

    abstract fun playlistDao(): PlaylistDao
}