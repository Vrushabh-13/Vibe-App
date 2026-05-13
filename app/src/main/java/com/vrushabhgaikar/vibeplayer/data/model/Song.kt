package com.vrushabhgaikar.vibeplayer.data.model

data class Song(
    val image: Int,
    val title: String,
    val artist: String,
    val like : Boolean = false
)