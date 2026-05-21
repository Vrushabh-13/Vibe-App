package com.vrushabhgaikar.vibeplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vrushabhgaikar.vibeplayer.data.local.entity.RecommendedSongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendationDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(
        song: RecommendedSongEntity
    )
    @Query("""
    SELECT * FROM recommended_songs
    ORDER BY isNewRecommendation DESC, id DESC
""")
    fun getRecommendedSongs():
            Flow<List<RecommendedSongEntity>>

    @Query("""
        UPDATE recommended_songs
        SET isNewRecommendation = 0
        WHERE id = :songId
    """)
    suspend fun markAsViewed(songId: Long)

    @Query("""
        SELECT EXISTS(
        SELECT 1
        FROM recommended_songs
        WHERE isNewRecommendation = 1
        )
    """)

    fun hasNewRecommendations():
            Flow<Boolean>

    @Query("DELETE FROM recommended_songs")
    suspend fun clearAll()

    @Query("""
    UPDATE recommended_songs
    SET isFav = :isFav
    WHERE id = :songId
""")
    suspend fun updateFavorite(songId: Long, isFav: Boolean)
}