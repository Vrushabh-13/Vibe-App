package com.vrushabhgaikar.vibeplayer.presentation.screens


import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel


@Composable
fun OfflineMediaScreen() {

    val context = LocalContext.current

    var mediaList by remember {
        mutableStateOf<List<MediaItemModel>>(emptyList())
    }

    val permissions = if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    ) {
        arrayOf(
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
        )
    } else {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) {

            val reader = MediaStoreReader(context)

            val audio = reader.getLocalAudio()

            val videos = reader.getLocalVideos()

            mediaList = audio + videos
        }

    LaunchedEffect(Unit) {
        launcher.launch(permissions)
    }

    LazyColumn {

        items(mediaList) { media ->

            Text(
                text = "${media.mediaType} : ${media.title}"
            )
        }
    }
}