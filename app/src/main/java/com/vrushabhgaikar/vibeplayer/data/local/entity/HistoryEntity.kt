package com.vrushabhgaikar.vibeplayer.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playback_history")
data class HistoryEntity(

    @PrimaryKey
    val mediaId: Long,

    val playedAt: Long
)