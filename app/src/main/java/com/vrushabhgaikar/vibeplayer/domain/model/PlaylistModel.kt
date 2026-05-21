package com.vrushabhgaikar.vibeplayer.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistModel(

    val name: String,

    val mediaIds: List<Long> = emptyList()
)