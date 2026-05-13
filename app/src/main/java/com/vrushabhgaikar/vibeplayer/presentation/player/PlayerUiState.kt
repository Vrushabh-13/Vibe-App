package com.vrushabhgaikar.vibeplayer.presentation.player


import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel

data class PlayerUiState(
    val currentMedia: MediaItemModel? = null,
    val isMiniPlayerVisible: Boolean = false,
    val isFullPlayerVisible: Boolean = false,
    val isPlaying: Boolean = false,
    val progress: Float = 0f,
    val duration: Long = 0L,
    val currentPosition: Long = 0L,
    val isRepeatEnabled: Boolean = false
)