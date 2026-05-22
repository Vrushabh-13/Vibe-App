package com.vrushabhgaikar.vibeplayer.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vrushabhgaikar.vibeplayer.data.local.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToHistory(
        historyEntity: HistoryEntity
    )

    @Query("""
        SELECT * FROM playback_history
        ORDER BY playedAt DESC
    """)
    fun getHistory():
            Flow<List<HistoryEntity>>

    @Query("""
        DELETE FROM playback_history
    """)
    suspend fun clearHistory()
}