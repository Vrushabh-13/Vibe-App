package com.vrushabhgaikar.vibeplayer.presentation.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel

class PlayerManager(
    context: Context
) {
    private val exoPlayer = ExoPlayer.Builder(context).build()
    fun play(media: MediaItemModel) {

        val mediaItem = MediaItem.fromUri(media.uri!!)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun resume() {
        exoPlayer.play()
    }
    fun stop() {
        exoPlayer.stop()
    }
    fun release() {
        exoPlayer.release()
    }
    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }
    fun currentPosition(): Long {
        return exoPlayer.currentPosition
    }
    fun duration(): Long {
        return exoPlayer.duration
    }
    fun isPlaying(): Boolean {
        return exoPlayer.isPlaying
    }
    fun getPlayer(): ExoPlayer =  exoPlayer

    fun currentMediaDuration(): Long {
        return exoPlayer.duration
    }

    fun setRepeatMode(enabled: Boolean){

        exoPlayer.repeatMode =
            if(enabled)
                androidx.media3.common.Player.REPEAT_MODE_ONE
            else
                androidx.media3.common.Player.REPEAT_MODE_OFF
    }


}