package com.vrushabhgaikar.vibeplayer.data.manager

import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object RecommendationManager{
    private val _recommendedSongs =
        MutableStateFlow<List<MediaItemModel>>(emptyList())

    val recommendedSongs =
        _recommendedSongs.asStateFlow()

    fun addRecommendation(song: MediaItemModel){
        _recommendedSongs.value = listOf(song) + _recommendedSongs.value
    }

    fun markAsViewed(id: Long?){
        _recommendedSongs.value = _recommendedSongs.value.map{
            if(it.id == id){
                it.copy(
                    isNewRecommendation = false
                )
            }else{
                it
            }
            }
        }
    fun hasNewRecommendations():Boolean
        {
        return _recommendedSongs.value.any{
            it.isNewRecommendation
        }
    }

    fun updateRecommendation(updatedMedia: MediaItemModel){
        _recommendedSongs.value =
            _recommendedSongs.value.map{
                if(it.id == updatedMedia.id) {
                    updatedMedia
                }else{
                    it
                }
            }
    }
}

