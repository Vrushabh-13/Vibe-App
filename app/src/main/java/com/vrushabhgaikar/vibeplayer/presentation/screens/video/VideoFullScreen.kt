package com.vrushabhgaikar.vibeplayer.presentation.screens.video

import PlayerManager
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoFullScreen(
    navController: NavController,
    onCloseFullScreen: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity

    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onDispose {
            onCloseFullScreen()
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = PlayerManager.getController()
                    useController = true

                    resizeMode = androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = {

                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            AppIcon(
                icon = com.vrushabhgaikar.vibeplayer.R.drawable.ic_arrow_left,
                contentDescription = null,
                tint = Color.White

            )
        }
    }

}