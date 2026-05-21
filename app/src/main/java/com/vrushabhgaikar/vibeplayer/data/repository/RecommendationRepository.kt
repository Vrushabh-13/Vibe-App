package com.vrushabhgaikar.vibeplayer.data.repository

import com.vrushabhgaikar.vibeplayer.data.local.dao.RecommendationDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.RecommendedSongEntity
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel

class RecommendationRepository(
    private val dao: RecommendationDao
) {

    fun getRecommendations() =
        dao.getRecommendedSongs()

    fun hasNewRecommendations() =
        dao.hasNewRecommendations()

    suspend fun addRecommendation(song: RecommendedSongEntity) {
        dao.insertRecommendation(song)
    }

    suspend fun markAsViewed(id: Long) {
        dao.markAsViewed(id)
    }

    suspend fun updateRecommendation(media: MediaItemModel) {
        dao.insertRecommendation(
            RecommendedSongEntity(
                id = media.id ?: 0L,
                title = media.title ?: "",
                artist = media.artist ?: "",
                songUrl = media.uri.toString(),
                thumbnailUrl = media.thumbnailUri.toString(),
                isNewRecommendation = media.isNewRecommendation
            )
        )
    }

    suspend fun clearAll() {
        dao.clearAll()
    }
    suspend fun updateFavorite(id: Long, isFav: Boolean) {
        dao.updateFavorite(id, isFav)
    }

}