package com.vrushabhgaikar.vibeplayer.presentation.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.data.repository.MediaRepositoryImpl
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.SourceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.xml.transform.Source

class HomeViewModel(
    application: Application
): AndroidViewModel(application){
    private val repository = MediaRepositoryImpl(
        MediaStoreReader(application)
    )

    private var isLoaded = false
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

    private val _filteredSongs =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val filteredSongs: StateFlow<List<MediaItemModel>>
            = _filteredSongs

    private val _songFilter = MutableStateFlow(SourceType.ALL.value)
    val songFilter: StateFlow<String> = _songFilter

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _trendingVideos =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val trendingVideos: StateFlow<List<MediaItemModel>>
            = _trendingVideos

    private val _videoFilter =
        MutableStateFlow(SourceType.ALL.value)

    val videoFilter: StateFlow<String> = _videoFilter

    private val _filteredVideos =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val filteredVideos: StateFlow<List<MediaItemModel>>
            = _filteredVideos

    private val _videoSearchQuery =
        MutableStateFlow("")

    val videoSearchQuery: StateFlow<String>
            = _videoSearchQuery

    fun setVideoFilter(filter: String) {

        _videoFilter.value = filter

        applyVideoFilters()
    }

    fun updateVideoSearchQuery(query: String) {

        _videoSearchQuery.value = query

        applyVideoFilters()
    }

    private fun applyVideoFilters() {

        val filteredByType = when(_videoFilter.value) {

            SourceType.ONLINE.value ->
                _allMediaList.value.filter {
                    it.mediaType == MediaType.VIDEO &&
                            it.sourceType == SourceType.ONLINE
                }

            SourceType.OFFLINE.value ->
                _allMediaList.value.filter {
                    it.mediaType == MediaType.VIDEO &&
                            it.sourceType == SourceType.OFFLINE
                }

            else ->
                _allMediaList.value.filter {
                    it.mediaType == MediaType.VIDEO
                }
        }

        _filteredVideos.value =
            if(_videoSearchQuery.value.isBlank()) {

                filteredByType

            } else {

                filteredByType.filter {

                    it.title?.contains(
                        _videoSearchQuery.value,
                        ignoreCase = true
                    ) == true
                }
            }
        _filteredSongs.value = _filteredSongs.value.toList()
    }



    fun updateSearchQuery(query: String){
        _searchQuery.value = query
        applySongFilters()
    }

    private fun applySongFilters(){
        val filteredByType = when(_songFilter.value){
            SourceType.ONLINE.value ->
                _allMediaList.value.filter {
                    it.mediaType == MediaType.AUDIO &&
                            it.sourceType == SourceType.ONLINE
                }

            SourceType.OFFLINE.value ->
                _allMediaList.value.filter {
                    it.mediaType == MediaType.AUDIO &&
                            it.sourceType == SourceType.OFFLINE
                }
            else ->
                _allMediaList.value.filter {
                    it.mediaType == MediaType.AUDIO
                }
        }
        _filteredSongs.value =
            if (_searchQuery.value.isBlank()){
                filteredByType
            }else{
                filteredByType.filter {
                    it.title?.contains(
                        _searchQuery.value,
                        ignoreCase = true
                    ) == true ||
                            it.artist?.contains(
                                _searchQuery.value,
                                ignoreCase = true
                            ) == true
                }
            }
    }


    fun getMediaById(id: Long): MediaItemModel? {
        return _allMediaList.value.find { it.id == id }
    }

    fun setSongFilter(filter: String){

        _songFilter.value = filter
        applySongFilters()

//        _filteredSongs.value =
//            when(filter){
//
//                SourceType.ONLINE.value ->
//                    _allMediaList.value.filter {
//                        it.mediaType == MediaType.AUDIO &&
//                                it.sourceType == SourceType.ONLINE
//                    }
//
//                SourceType.OFFLINE.value ->
//                    _allMediaList.value.filter {
//                        it.mediaType == MediaType.AUDIO &&
//                                it.sourceType == SourceType.OFFLINE
//                    }
//
//                else ->
//                    _allMediaList.value.filter {
//                        it.mediaType == MediaType.AUDIO
//                    }
//            }
    }

//    fun getFilteredSongs(): List<MediaItemModel>{
//        return when(_songFilter.value){
//            SourceType.ONLINE.value -> _allMediaList.value.filter {
//                it.mediaType == MediaType.AUDIO && it.sourceType == SourceType.ONLINE
//            }
//            SourceType.OFFLINE.value -> _allMediaList.value.filter {
//                it.mediaType == MediaType.AUDIO && it.sourceType == SourceType.OFFLINE
//            }
//            else -> _allMediaList.value.filter {
//                it.mediaType == MediaType.AUDIO
//            }
//        }
//    }


    fun loadMedia() {
        if(isLoaded) return

        isLoaded = true
        viewModelScope.launch {

            _audioListLocal.value =
                repository.getLocalAudio()

            _videoListLocal.value =
                repository.getLocalVideos()

             _allMediaList.value =
                repository.getAllMedia()

//            setSongFilter(SourceType.ALL.value)
              applySongFilters()


            updateHomeSections()
            applyVideoFilters()


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
                .sortedBy { it.id }
                .take(6)

        _trendingVideos.value =
            _allMediaList.value
                .filter { it.mediaType == MediaType.VIDEO }
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
//        setSongFilter(_songFilter.value)
        applySongFilters()
        applyVideoFilters()
        updateHomeSections()

        return updatedMedia
    }

}