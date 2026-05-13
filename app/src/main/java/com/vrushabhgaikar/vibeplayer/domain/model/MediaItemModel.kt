package com.vrushabhgaikar.vibeplayer.domain.model

import android.net.Uri

data class MediaItemModel(
    val id: Long? = 0,
    val title: String? = "",
    val artist: String? = "",
    val uri: Uri? = null,
    val duration: Long = 0,
    val thumbnailUri: Uri? = null,
    val isFav: Boolean = false ,
    val playedDuration: Long = 0,
    val isPlaying: Boolean = false,
    val mediaType: MediaType = MediaType.AUDIO,
    val sourceType: SourceType = SourceType.OFFLINE,
    val playedAt: Long = 0L
)