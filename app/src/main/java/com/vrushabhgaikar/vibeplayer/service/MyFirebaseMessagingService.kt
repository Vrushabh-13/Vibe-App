package com.vrushabhgaikar.vibeplayer.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.vrushabhgaikar.vibeplayer.MainActivity
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.data.local.database.DatabaseProvider
import com.vrushabhgaikar.vibeplayer.data.local.entity.RecommendedSongEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFirebaseMessagingService :
    FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("FCM_TOKEN", token)
    }

    override fun onMessageReceived(
        remoteMessage: RemoteMessage
    ) {

        val title =
            remoteMessage.data["title"] ?: ""

        val artist =
            remoteMessage.data["artist"] ?: ""

        val songUrl =
            remoteMessage.data["songUrl"] ?: ""

        val thumbnailUrl =
            remoteMessage.data["thumbnailUrl"] ?: ""

//        val songId =
//            System.currentTimeMillis()

        val songId =
            remoteMessage.data["songId"]
                ?.toLongOrNull()
                ?: System.currentTimeMillis()

        CoroutineScope(Dispatchers.IO).launch {

            val dao =
                DatabaseProvider
                    .getDatabase(applicationContext)
                    .recommendationDao()

            dao.insertRecommendation(
                RecommendedSongEntity(
                    id = songId,
                    title = title,
                    artist = artist,
                    songUrl = songUrl,
                    thumbnailUrl = thumbnailUrl,
                    isNewRecommendation = true
                )
            )
        }

        showNotification(title = title, artist = artist, songId = songId)
    }

    private fun showNotification(
        title: String,
        artist: String,
        songId: Long
    ) {

        val channelId = "vibe_notifications"

        val manager =
            getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Vibe Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )

        manager.createNotificationChannel(channel)

        val intent = Intent(
            this,
            MainActivity::class.java
        ).apply {

            putExtra("songId", songId)

            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        val notification =
            NotificationCompat.Builder(
                this,
                channelId
            )
                .setContentTitle(title)
                .setContentText(artist)
                .setSmallIcon(R.drawable.img_placeholder_song)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        manager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }
}