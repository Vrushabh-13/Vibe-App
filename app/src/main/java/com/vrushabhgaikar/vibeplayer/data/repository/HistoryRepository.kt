package com.vrushabhgaikar.vibeplayer.data.repository


import com.vrushabhgaikar.vibeplayer.data.local.dao.HistoryDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.HistoryEntity

class HistoryRepository(
    private val dao: HistoryDao
) {

    fun getHistory() =
        dao.getHistory()

    suspend fun addToHistory(
        mediaId: Long
    ) {
        dao.addToHistory(
            HistoryEntity(
                mediaId = mediaId,
                playedAt = System.currentTimeMillis()
            )
        )
    }
}