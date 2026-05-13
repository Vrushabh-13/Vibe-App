package com.vrushabhgaikar.vibeplayer.data.repository

import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel

interface MediaRepository {
    fun getLocalAudio(): List<MediaItemModel>

    fun getLocalVideos(): List<MediaItemModel>

    fun getOnlineMedia(): List<MediaItemModel>

    fun getAllMedia(): List<MediaItemModel>
}