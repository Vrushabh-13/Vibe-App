package com.vrushabhgaikar.vibeplayer.data.repository

import com.vrushabhgaikar.vibeplayer.data.local.dao.PlaylistDao
import com.vrushabhgaikar.vibeplayer.data.local.entity.PlaylistEntity
import com.vrushabhgaikar.vibeplayer.data.local.entity.PlaylistSongCrossRef

class PlaylistRepository(
    private val dao: PlaylistDao
) {

    fun getPlaylists() =
        dao.getPlaylists()

    suspend fun createPlaylist(
        name: String
    ) {
        dao.createPlaylist(
            PlaylistEntity(name)
        )
    }

    suspend fun savePlaylistSongs(
        playlistName: String,
        mediaIds: List<Long>
    ) {

        dao.clearPlaylistSongs(
            playlistName
        )

        mediaIds.forEach { mediaId ->

            dao.addSongToPlaylist(
                PlaylistSongCrossRef(
                    playlistName = playlistName,
                    mediaId = mediaId
                )
            )
        }
    }

    suspend fun getPlaylistSongIds(
        playlistName: String
    ): List<Long> {

        return dao.getPlaylistSongIds(
            playlistName
        )
    }
}