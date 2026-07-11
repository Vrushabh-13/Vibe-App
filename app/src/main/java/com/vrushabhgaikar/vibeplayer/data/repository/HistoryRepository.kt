package com.vrushabhgaikar.vibeplayer.data.repository


import PlayerManager
import com.vrushabhgaikar.vibeplayer.data.local.dao.HistoryDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.HistoryEntity
import java.time.Duration

class HistoryRepository(
    private val dao: HistoryDao
) {

    fun getHistory() =
        dao.getHistory()

    suspend fun addToHistory(
        mediaId: Long,
        playedDuration: Long
    ) {
        dao.addToHistory(
            HistoryEntity(
                mediaId = mediaId,
                playedAt = System.currentTimeMillis(),
                playedDuration = playedDuration
            )
        )
    }
}