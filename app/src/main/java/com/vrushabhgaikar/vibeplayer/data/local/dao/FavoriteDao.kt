package com.vrushabhgaikar.vibeplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vrushabhgaikar.vibeplayer.data.local.entity.FavoriteSongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(
        favoriteSong: FavoriteSongEntity
    )

    @Query("""
        DELETE FROM favorite_songs
        WHERE mediaId = :mediaId
    """)
    suspend fun removeFromFavorites(
        mediaId: Long
    )

    @Query("""
        SELECT * FROM favorite_songs
    """)
    fun getFavoriteSongs():
            Flow<List<FavoriteSongEntity>>
}