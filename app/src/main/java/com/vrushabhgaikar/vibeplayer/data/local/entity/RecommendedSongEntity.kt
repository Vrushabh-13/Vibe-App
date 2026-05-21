package com.vrushabhgaikar.vibeplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommended_songs")
data class RecommendedSongEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val artist: String,
    val songUrl: String,
    val thumbnailUrl: String,
    val isNewRecommendation: Boolean = true,
    val isFav: Boolean = false
)