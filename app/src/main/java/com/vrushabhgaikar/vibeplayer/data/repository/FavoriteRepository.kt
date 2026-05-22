package com.vrushabhgaikar.vibeplayer.data.repository

import com.vrushabhgaikar.vibeplayer.data.local.dao.FavoriteDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.FavoriteSongEntity

class FavoriteRepository(
    private val dao: FavoriteDao
) {

    fun getFavorites() =
        dao.getFavoriteSongs()

    suspend fun addToFavorites(
        mediaId: Long
    ) {
        dao.addToFavorites(
            FavoriteSongEntity(mediaId)
        )
    }

    suspend fun removeFromFavorites(
        mediaId: Long
    ) {
        dao.removeFromFavorites(mediaId)
    }
}