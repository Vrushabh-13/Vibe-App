package com.vrushabhgaikar.vibeplayer.presentation.player

import PlayerManager
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PlayerViewModel(application: Application) : AndroidViewModel(application) {


    private val _playerState = MutableStateFlow(PlayerUiState())
    val playerState = _playerState.asStateFlow()

    init {
        observeProgress()
    }


    fun onMediaSelected(media: MediaItemModel) {

        PlayerManager.initialize(getApplication()) {
            PlayerManager.play(media)
        }

        if (media.playedDuration > 0L) {
            PlayerManager.seekTo(media.playedDuration)
        }

        _playerState.value = _playerState.value.copy(
            currentMedia = media,

            isMiniPlayerVisible = true,


            isPlaying = true
        )
    }

//    fun syncFavorite(mediaId: Long, homeViewModel: HomeViewModel) {
//        val updated = homeViewModel.getMediaById(mediaId)
//        updated?.let {
//            _playerState.value = _playerState.value.copy(
//                currentMedia = it
//            )
//        }
//    }

    fun togglePlayPause() {
        if (PlayerManager.isPlaying()) {
            PlayerManager.pause()
        } else {
            PlayerManager.resume()
        }

        _playerState.value = _playerState.value.copy(
            isPlaying = PlayerManager.isPlaying()
        )
    }

    fun dismissFullPlayer() {
        _playerState.value = _playerState.value.copy(
            isFullPlayerVisible = false
        )
    }

    fun seekTo(progress: Float) {
        val duration = PlayerManager.duration()
        val newPosition = (progress * duration).toLong()
        PlayerManager.seekTo(newPosition)
    }

    fun seekForward10() {
        val newPos = PlayerManager.currentPosition() + 10_000
        PlayerManager.seekTo(newPos)
    }

    fun seekBackward10() {
        val newPos = PlayerManager.currentPosition() - 10_000
        PlayerManager.seekTo(newPos.coerceAtLeast(0))
    }


    fun onMediaUpdated(updatedMedia: MediaItemModel) {

        val current = _playerState.value.currentMedia ?: return

        if (current.id == updatedMedia.id) {
            _playerState.value = _playerState.value.copy(
                currentMedia = updatedMedia
            )
        }
    }

    fun updatedCurrentMedia(media: MediaItemModel) {
        _playerState.value =
            _playerState.value.copy(currentMedia = media)
    }

    fun toggleRepeat() {

        val enabled = !_playerState.value.isRepeatEnabled
        PlayerManager.setRepeatMode(enabled)
        _playerState.value = _playerState.value.copy(
            isRepeatEnabled = enabled
        )
    }

    private fun observeProgress() {

        viewModelScope.launch {
            while (true) {

                val currentPosition = PlayerManager.currentPosition()
                val duration = PlayerManager.duration()
                val currentMedia = _playerState.value.currentMedia
                val updatedMedia = currentMedia?.copy(
                    playedDuration = currentPosition,
                    playedAt = System.currentTimeMillis()
                )
                _playerState.value = _playerState.value.copy(
                    currentPosition = currentPosition,
                    duration = duration,
                    currentMedia = updatedMedia,
                    isPlaying = PlayerManager.isPlaying()
                )
                delay(500)
            }
        }
    }

    fun openVideoFullScreen() {

        _playerState.value =
            _playerState.value.copy(

                isVideoFullScreen = true,

                isFullPlayerVisible = false
            )
    }

    fun closeVideoFullScreen() {

        _playerState.value =
            _playerState.value.copy(
                isVideoFullScreen = false
            )
    }


    fun onMiniPlayerClick() {
        _playerState.value = _playerState.value.copy(
            isFullPlayerVisible = true
        )
    }

}