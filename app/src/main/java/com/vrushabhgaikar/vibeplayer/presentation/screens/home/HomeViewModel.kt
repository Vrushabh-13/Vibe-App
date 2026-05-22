package com.vrushabhgaikar.vibeplayer.presentation.screens.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.data.local.database.DatabaseProvider
import com.vrushabhgaikar.vibeplayer.data.local.entity.RecommendedSongEntity
import com.vrushabhgaikar.vibeplayer.data.repository.MediaRepositoryImpl
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.PlaylistModel
import com.vrushabhgaikar.vibeplayer.domain.model.SourceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.xml.transform.Source
import com.vrushabhgaikar.vibeplayer.data.manager.RecommendationManager
import com.vrushabhgaikar.vibeplayer.data.repository.RecommendationRepository
import com.vrushabhgaikar.vibeplayer.data.local.entity.FavoriteSongEntity
import com.vrushabhgaikar.vibeplayer.data.repository.FavoriteRepository
import com.vrushabhgaikar.vibeplayer.data.repository.HistoryRepository
import com.vrushabhgaikar.vibeplayer.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.first

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

    private val _historyList =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val historyList: StateFlow<List<MediaItemModel>> =
        _historyList

    private val db = DatabaseProvider.getDatabase(application)
    private val repo = RecommendationRepository(db.recommendationDao())

    private val favoriteRepository =
        FavoriteRepository(db.favoriteDao())

    private val historyRepository =
        HistoryRepository(db.historyDao())

    private val playlistRepository =
        PlaylistRepository(db.playlistDao())

    val recommendedSongs = repo.getRecommendations()


    private val _playlists =
        MutableStateFlow<List<PlaylistModel>>(emptyList())

    val playlists: StateFlow<List<PlaylistModel>>
            = _playlists

//    private val _recommendedSongs =
//        MutableStateFlow<List<MediaItemModel>>(emptyList())
//
//    val recommendedSongs: StateFlow<List<MediaItemModel>>
//            = _recommendedSongs

//    val recommendedSongs = RecommendationManager.recommendedSongs

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

//    private val _playlists =
//        MutableStateFlow<List<PlaylistModel>>(emptyList())
//
//    val playlists: StateFlow<List<PlaylistModel>>
//            = _playlists



//    fun createPlaylist(name: String) {
//
//        if (name.isBlank()) return
//
//        val current = _playlists.value.toMutableList()
//
//        val alreadyExists =
//            current.any {
//                it.name == name
//            }
//
//        if (!alreadyExists) {
//
//            current.add(
//                PlaylistModel(name = name)
//            )
//
//            _playlists.value = current
//        }
//    }

    fun createPlaylist(name: String) {

        if (name.isBlank()) return

        viewModelScope.launch {

            playlistRepository.createPlaylist(name)
        }
    }

//    fun saveSongsToPlaylist(
//        playlistName: String,
//        mediaIds: List<Long>
//    ) {
//        if (playlistName.isBlank())return
//        _playlists.value =
//            _playlists.value.map { playlist ->
//
//                if (playlist.name == playlistName) {
//
//                    playlist.copy(
//                        mediaIds = mediaIds.distinct()
//                    )
//
//                } else {
//                    playlist
//                }
//            }
//    }

    fun saveSongsToPlaylist(
        playlistName: String,
        mediaIds: List<Long>
    ) {

        viewModelScope.launch {

            playlistRepository.savePlaylistSongs(
                playlistName = playlistName,
                mediaIds = mediaIds
            )

            loadPlaylistsFromRoom()
        }
    }

    fun getPlaylistSongs(
        playlistName: String
    ): List<MediaItemModel> {

        val playlist =
            _playlists.value.find {
                it.name == playlistName
            }

        return _allMediaList.value.filter { media ->

            playlist?.mediaIds?.contains(media.id) == true
        }
    }

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

    fun markRecommendationViewed(songId: Long) {

        _allMediaList.value =
            _allMediaList.value.map {

                if (it.id == songId) {
                    it.copy(
                        isNewRecommendation = false
                    )
                } else {
                    it
                }
            }

        viewModelScope.launch {
            repo.markAsViewed(songId)
        }
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

    fun syncRecommendedSong(
        media: MediaItemModel
    ) {

        val exists =
            _allMediaList.value.any {
                it.id == media.id
            }

        if (!exists) {

            _allMediaList.value =
                listOf(media) + _allMediaList.value

        } else {

            _allMediaList.value =
                _allMediaList.value.map {

                    if (it.id == media.id) {

                        it.copy(
                            isNewRecommendation =
                                media.isNewRecommendation
                        )

                    } else {
                        it
                    }
                }
        }
    }
    private fun syncFavoritesFromRoom() {

        viewModelScope.launch {

            favoriteRepository
                .getFavorites()
                .collect { favorites ->

                    val favoriteIds =
                        favorites.map { it.mediaId }

                    _allMediaList.value =
                        _allMediaList.value.map { media ->

                            media.copy(
                                isFav =
                                    favoriteIds.contains(media.id)
                            )
                        }

                    updateHomeSections()
                    applySongFilters()
                    applyVideoFilters()
                }
        }
    }

    private fun syncHistoryFromRoom() {

        viewModelScope.launch {

            historyRepository
                .getHistory()
                .collect { historyList ->

                    _allMediaList.value =
                        _allMediaList.value.map { media ->

                            val history =
                                historyList.find {
                                    it.mediaId == media.id
                                }

                            if (history != null) {

                                media.copy(
                                    playedAt = history.playedAt
                                )

                            } else {
                                media
                            }
                        }

                    updateHomeSections()
                }
        }
    }
    private fun loadPlaylistsFromRoom() {

        viewModelScope.launch {

            _playlists.value = emptyList()

            playlistRepository
                .getPlaylists()
                .collect { playlistEntities ->

                    val playlistModels =
                        playlistEntities.map { entity ->

                            val mediaIds =
                                playlistRepository
                                    .getPlaylistSongIds(entity.name)

                            PlaylistModel(
                                name = entity.name,
                                mediaIds = mediaIds
                            )
                        }

                    _playlists.value = playlistModels
                }
        }
    }


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

            syncFavoritesFromRoom()

            syncHistoryFromRoom()

            loadPlaylistsFromRoom()

//            setSongFilter(SourceType.ALL.value)
              applySongFilters()


            updateHomeSections()
            applyVideoFilters()

//            repo.clearAll()
//
//            _allMediaList.value
//                .filter {
//                    it.mediaType == MediaType.AUDIO
//                }
//                .take(5).forEach { media ->
//                viewModelScope.launch {
//                    repo.addRecommendation(
//                        RecommendedSongEntity(
//                            id = media.id ?: 0L,
//                            title = media.title ?: "",
//                            artist = media.artist ?: "",
//                            songUrl = media.uri.toString(),
//                            thumbnailUrl = media.thumbnailUri.toString(),
//                            isNewRecommendation = false
//                        )
//                    )
//                }
//            }
            _allMediaList.value
                .filter {
                    it.mediaType == MediaType.AUDIO
                }
                .take(5)
                .forEach { media ->

                    viewModelScope.launch {

                        repo.addRecommendation(
                            RecommendedSongEntity(
                                id = media.id ?: 0L,
                                title = media.title ?: "",
                                artist = media.artist ?: "",
                                songUrl = media.uri.toString(),
                                thumbnailUrl = media.thumbnailUri.toString(),
                                isNewRecommendation = false
                            )
                        )
                    }
                }



//            _allMediaList.value.firstOrNull()?.let {media ->
//                RecommendationManager.addRecommendation(
//                    media.copy(
//                        isNewRecommendation = true
//                    )
//                )
//            }


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

        viewModelScope.launch {

            media.id?.let {

                historyRepository.addToHistory(it)
            }
        }
    }
    private fun updateHomeSections() {

        _recentlyPlayedList.value =
            _allMediaList.value
                .filter { it.playedAt > 0L }
                .sortedByDescending { it.playedAt }
                .take(5)

//        _recommendedSongs.value =
//            _allMediaList.value
//                .filter { it.mediaType == MediaType.AUDIO }
//                .sortedBy { it.id }
//                .take(6)

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

        _historyList.value =
            _allMediaList.value
                .filter { it.playedAt > 0L }
                .sortedByDescending { it.playedAt }
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

//        viewModelScope.launch {
//            repo.updateFavorite(
//                id = updatedMedia.id ?: return@launch,
//                isFav = updatedMedia.isFav
//            )
//        }

        viewModelScope.launch {

            val mediaId =
                updatedMedia.id ?: return@launch

            if (updatedMedia.isFav) {

                favoriteRepository
                    .addToFavorites(mediaId)

            } else {

                favoriteRepository
                    .removeFromFavorites(mediaId)
            }

            repo.updateFavorite(
                id = mediaId,
                isFav = updatedMedia.isFav
            )
        }
//        RecommendationManager.updateRecommendation(updatedMedia)

        return updatedMedia
    }

}