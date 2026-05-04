package com.vrushabhgaikar.vibeplayer.player

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import retrofit2.http.Url

class MusicPlayerManager(context: Context) {
    val player: ExoPlayer = ExoPlayer.Builder(context).build()

    fun play(url: Url){
        // will implement later
    }

    fun pause(){
        player.pause()
    }

    fun release(){
        player.release()
    }
}