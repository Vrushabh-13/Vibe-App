package com.vrushabhgaikar.vibeplayer.data.repository

import com.vrushabhgaikar.vibeplayer.data.dummy.dummyMediaList
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel

class MediaRepositoryImpl(
    private val mediaStoreReader: MediaStoreReader
) : MediaRepository {
    override fun getLocalAudio(): List<MediaItemModel> {

        return mediaStoreReader.getLocalAudio()
    }

    override fun getLocalVideos(): List<MediaItemModel> {

        return mediaStoreReader.getLocalVideos()
    }

    override fun getOnlineMedia(): List<MediaItemModel> {

        return dummyMediaList
    }

    override fun getAllMedia(): List<MediaItemModel> {

        return getLocalAudio() +
                getLocalVideos() +
                getOnlineMedia()
    }
}