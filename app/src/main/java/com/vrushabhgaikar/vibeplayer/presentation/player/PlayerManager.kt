import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.service.MusicService

object PlayerManager{
    private var controller: MediaController? = null

    fun initialize(
        context: Context,
        onReady: () -> Unit
        ){
        if(controller != null){
            onReady()
            return
        }

        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicService::class.java)
        )

        val controllerFuture = MediaController.Builder(context,sessionToken).buildAsync()

        controllerFuture.addListener({
            controller = controllerFuture.get()

            onReady()
        }, MoreExecutors.directExecutor())

    }
    fun play(media: MediaItemModel) {

        val mediaItem = MediaItem.Builder()
            .setUri(media.uri)
            .setMediaMetadata(
                androidx.media3.common.MediaMetadata.Builder()
                    .setTitle(media.title)
                    .setArtist(media.artist)
                    .setArtworkUri(media.thumbnailUri)
                    .build()
            )
            .build()

        controller?.apply {
            setMediaItem(mediaItem, true)
            play()
        }
    }
//    fun play(media: MediaItemModel) {
//
////        val mediaItem = MediaItem.fromUri(url)
//        val mediaItem = MediaItem.Builder()
//            .setUri(media.uri)
//            .setMediaMetadata(
//                androidx.media3.common.MediaMetadata.Builder()
//                    .setTitle(media.title)
//                    .setArtist(media.artist)
//                    .setArtworkUri(media.thumbnailUri)
//                    .build()
//            )
//            .build()
//
////
//
//        controller?.apply {
//            setMediaItem(mediaItem)
//            prepare()
//            play()
//        }
//    }
    fun pause() {
        controller?.pause()
    }

    fun resume() {
        controller?.play()
    }

    fun seekTo(position: Long) {
        controller?.seekTo(position)
    }

    fun currentPosition(): Long {
        return controller?.currentPosition ?: 0L
    }

    fun duration(): Long {
        return controller?.duration ?: 0L
    }

    fun isPlaying(): Boolean {
        return controller?.isPlaying ?: false
    }

    fun setRepeatMode(enabled: Boolean) {

        controller?.repeatMode =
            if (enabled)
                androidx.media3.common.Player.REPEAT_MODE_ONE
            else
                androidx.media3.common.Player.REPEAT_MODE_OFF
    }

    fun getController(): MediaController? {
        return controller
    }
}




















//package com.vrushabhgaikar.vibeplayer.presentation.player
//
//import android.content.Context
//import androidx.media3.common.MediaItem
//import androidx.media3.exoplayer.ExoPlayer
//import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
//
//class PlayerManager(
//    context: Context
//) {
//    private val exoPlayer = ExoPlayer.Builder(context).build()
//    fun play(media: MediaItemModel) {
//
//        val mediaItem = MediaItem.fromUri(media.uri!!)
//
//        exoPlayer.setMediaItem(mediaItem)
//        exoPlayer.prepare()
//        exoPlayer.play()
//    }
//
//    fun pause() {
//        exoPlayer.pause()
//    }
//
//    fun resume() {
//        exoPlayer.play()
//    }
//    fun stop() {
//        exoPlayer.stop()
//    }
//    fun release() {
//        exoPlayer.release()
//    }
//    fun seekTo(position: Long) {
//        exoPlayer.seekTo(position)
//    }
//    fun currentPosition(): Long {
//        return exoPlayer.currentPosition
//    }
//    fun duration(): Long {
//        return exoPlayer.duration
//    }
//    fun isPlaying(): Boolean {
//        return exoPlayer.isPlaying
//    }
//    fun getPlayer(): ExoPlayer =  exoPlayer
//
//    fun currentMediaDuration(): Long {
//        return exoPlayer.duration
//    }
//
//    fun setRepeatMode(enabled: Boolean){
//
//        exoPlayer.repeatMode =
//            if(enabled)
//                androidx.media3.common.Player.REPEAT_MODE_ONE
//            else
//                androidx.media3.common.Player.REPEAT_MODE_OFF
//    }
//
//
//}

