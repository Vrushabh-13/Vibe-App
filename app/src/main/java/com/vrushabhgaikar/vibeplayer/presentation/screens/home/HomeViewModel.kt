package com.vrushabhgaikar.vibeplayer.presentation.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.data.repository.MediaRepositoryImpl
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
): AndroidViewModel(application){
    private val repository = MediaRepositoryImpl(
        MediaStoreReader(application)
    )
    private val _audioListLocal = MutableStateFlow<List<MediaItemModel>>(emptyList())
    val audioListLocal: StateFlow<List<MediaItemModel>> = _audioListLocal

    private val _videoListLocal = MutableStateFlow<List<MediaItemModel>>(emptyList())
    val videoListLocal: StateFlow<List<MediaItemModel>> = _videoListLocal


    private val _allMediaList = MutableStateFlow<List<MediaItemModel>>(emptyList())
    val allMediaList: StateFlow<List<MediaItemModel>> = _allMediaList

    private val _recentlyPlayedList =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val recentlyPlayedList: StateFlow<List<MediaItemModel>>
            = _recentlyPlayedList

    private val _recommendedSongs =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val recommendedSongs: StateFlow<List<MediaItemModel>>
            = _recommendedSongs

    private val _continueListeningList =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val continueListeningList: StateFlow<List<MediaItemModel>>
            = _continueListeningList

    private val _favoriteSongs =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val favoriteSongs: StateFlow<List<MediaItemModel>>
            = _favoriteSongs


    fun loadMedia() {

        viewModelScope.launch {

            _audioListLocal.value =
                repository.getLocalAudio()

            _videoListLocal.value =
                repository.getLocalVideos()

            _allMediaList.value =
                repository.getAllMedia()

            updateHomeSections()


        }
    }

    fun updatePlayedMedia(media: MediaItemModel){
        _allMediaList.value = _allMediaList.value.map {
            if(it.id == media.id){
                it.copy(
                    playedAt = System.currentTimeMillis(),
                    playedDuration = 1000L
                )
            }else{
                it
            }
        }
        updateHomeSections()
    }
    private fun updateHomeSections() {

        _recentlyPlayedList.value =
            _allMediaList.value
                .filter { it.playedAt > 0L }
                .sortedByDescending { it.playedAt }
                .take(5)

        _recommendedSongs.value =
            _allMediaList.value
                .filter { it.mediaType == MediaType.AUDIO }
                .take(6)

        _continueListeningList.value =
            _allMediaList.value
                .filter {
                    it.playedDuration > 0L &&
                            it.playedDuration < it.duration
                }
                .sortedByDescending { it.playedAt }
                .take(6)

        _favoriteSongs.value =
            _allMediaList.value.filter { it.isFav }
    }
    fun toggleFavorite(media: MediaItemModel): MediaItemModel {

        var updatedMedia = media

        _allMediaList.value =
            _allMediaList.value.map {

                if (it.id == media.id) {

                    updatedMedia = it.copy(
                        isFav = !it.isFav
                    )

                    updatedMedia

                } else {
                    it
                }
            }

        updateHomeSections()

        return updatedMedia
    }

}