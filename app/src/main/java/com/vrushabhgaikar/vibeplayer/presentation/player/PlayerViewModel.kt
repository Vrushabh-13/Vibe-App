package com.vrushabhgaikar.vibeplayer.presentation.player

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

    private val playerManager = PlayerManager(application)


    private val _playerState = MutableStateFlow(PlayerUiState())
    val playerState = _playerState.asStateFlow()

    init {
        observeProgress()
    }

    fun onMediaSelected(media: MediaItemModel){
        playerManager.play(media)

        if (media.playedDuration > 0L) {
            playerManager.seekTo(media.playedDuration)
        }
        _playerState.value = _playerState.value.copy(
            currentMedia = media,
            isMiniPlayerVisible = true,
            isPlaying = true
        )
    }
    fun syncFavorite(mediaId: Long, homeViewModel: HomeViewModel) {
        val updated = homeViewModel.getMediaById(mediaId)
        updated?.let {
            _playerState.value = _playerState.value.copy(
                currentMedia = it
            )
        }
    }

    fun togglePlayPause(){
        if(playerManager.isPlaying()){
            playerManager.pause()
        }else{
            playerManager.resume()
        }

        _playerState.value = _playerState.value.copy(
                isPlaying = playerManager.isPlaying()
        )
    }

    fun dismissFullPlayer(){
        _playerState.value = _playerState.value.copy(
            isFullPlayerVisible = false
        )
    }

    fun seekTo(progress: Float){
        val duration = playerManager.duration()
        val newPosition = (progress * duration).toLong()
        playerManager.seekTo(newPosition)
    }

    fun seekForward10(){
        val newPos = playerManager.currentPosition() + 10_000
        playerManager.seekTo(newPos)
    }

    fun seekBackward10(){
        val newPos = playerManager.currentPosition() - 10_000
        playerManager.seekTo(newPos.coerceAtLeast(0))
    }

//    fun toggleLike(){
//        val currentMedia = _playerState.value.currentMedia ?: return
//        val updatedMedia = currentMedia.copy(
//            isFav =  !currentMedia.isFav
//        )
//        _playerState.value = _playerState.value.copy(
//            currentMedia = updatedMedia
//        )
//    }

    fun onMediaUpdated(updatedMedia: MediaItemModel) {

        val current = _playerState.value.currentMedia ?: return

        if (current.id == updatedMedia.id) {
            _playerState.value = _playerState.value.copy(
                currentMedia = updatedMedia
            )
        }
    }
fun updatedCurrentMedia(media: MediaItemModel){
    _playerState.value =
        _playerState.value.copy(currentMedia = media)
}

    fun toggleRepeat(){

        val enabled = !_playerState.value.isRepeatEnabled
        playerManager.setRepeatMode(enabled)
        _playerState.value = _playerState.value.copy(
            isRepeatEnabled = enabled
        )
    }

    private fun observeProgress() {

        viewModelScope.launch {
            while (true) {

                val currentPosition = playerManager.currentPosition()
                val duration = playerManager.duration()
                val currentMedia = _playerState.value.currentMedia
                val updatedMedia = currentMedia?.copy(
                    playedDuration = currentPosition,
                    playedAt = System.currentTimeMillis()
                )
                _playerState.value = _playerState.value.copy(
                    currentPosition = currentPosition,
                    duration = duration,
                    currentMedia = updatedMedia,
                    isPlaying = playerManager.isPlaying()
                )
                delay(500)
            }
        }
    }
    override fun onCleared() {
            super.onCleared()
            playerManager.release()
    }







//    private val _playerState = MutableStateFlow(PlayerUiState())
//    val playerState = _playerState.asStateFlow()
//
//    fun onSongSelected(media: MediaItemModel) {
//        _playerState.value = PlayerUiState(
//            currentMedia = media,
//            isMiniPlayerVisible = true,
//            isPlaying = true
//        )
//    }
//
    fun onMiniPlayerClick() {
        _playerState.value = _playerState.value.copy(
            isFullPlayerVisible = true
        )
    }
//
//    fun onDismissFullPlayer() {
//        _playerState.value = _playerState.value.copy(
//            isFullPlayerVisible = false
//        )
//    }
//
//    fun togglePlayPause() {
//        _playerState.value = _playerState.value.copy(
//            isPlaying = !_playerState.value.isPlaying
//        )
//    }
}