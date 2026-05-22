package com.vrushabhgaikar.vibeplayer.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "playlist_songs",
    primaryKeys = ["playlistName", "mediaId"]
)
data class PlaylistSongCrossRef(

    val playlistName: String,

    val mediaId: Long
)