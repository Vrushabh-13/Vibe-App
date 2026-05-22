package com.vrushabhgaikar.vibeplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_songs")
data class FavoriteSongEntity(

    @PrimaryKey
    val mediaId: Long
)