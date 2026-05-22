package com.vrushabhgaikar.vibeplayer.presentation.player

import PlayerManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White
import com.vrushabhgaikar.vibeplayer.utils.TimeUtils

@Composable
fun VideoPlayerContent(
    navController: NavHostController,
    uiState: PlayerUiState,
    onSeek: (Float) -> Unit,
    onPlayPause: () -> Unit,
    onForward10: () -> Unit,
    onBack10: () -> Unit,
    onFullScreen: () -> Unit,
    isFav: () -> Unit
) {


    val progress =
        if (uiState.duration > 0)
            uiState.currentPosition.toFloat() / uiState.duration.toFloat()
        else 0f
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(16.dp)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {

            // 🔹 Video Thumbnail
            Box(
                modifier = Modifier
                    .border(
                        width = 0.1.dp,
                        color = LightGray,
                        shape = RoundedCornerShape(11)
                    )
            ) {
                AndroidView(
                    factory = { context ->

                        PlayerView(context).apply {

                            player = PlayerManager.getController()

                            useController = false
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }


            // 🔹 Dark overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
            )


            // 🔹 Playback controls
            Row(
                modifier = Modifier
                    .align(Alignment.Center),

                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.spacedBy(26.dp)
            ) {

                // rewind
                Icon(
                    painterResource(id = R.drawable.ic_10_back),
                    null,
                    tint = White,
                    modifier = Modifier
                        .size(44.dp)
                        .clickable {
                            onBack10()
                        }
                )

                // play pause
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(
                            Color.Black.copy(alpha = 0.45f),
                            CircleShape
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        if (uiState.isPlaying)
                            painterResource(id = R.drawable.ic_pause)
                        else
                            painterResource(id = R.drawable.ic_play),

                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { onPlayPause() }
                    )

                }

                // forward
                Icon(
                    painterResource(id = R.drawable.ic_10_forward),
                    null,
                    tint = White,
                    modifier = Modifier
                        .size(44.dp)
                        .clickable { onForward10() }
                )
            }



            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                Slider(
                    value = progress,
                    onValueChange = { newValue ->
                        val newPosition = (newValue * uiState.duration).toLong()
                        PlayerManager.seekTo(newPosition)
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = PurplePrimary,
                        activeTrackColor = PurplePrimary,
                        inactiveTrackColor = White.copy(alpha = 0.3f)
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = TimeUtils.formatDuration(uiState.currentPosition),
                        color = White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = TimeUtils.formatDuration(uiState.duration),
                        color = White
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Icon(
                        painterResource(id = R.drawable.ic_fullscreen),
                        null,
                        tint = White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                onFullScreen()
                            }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                AppText(
                    text = uiState.currentMedia?.title ?: "",
                    color = White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = uiState.currentMedia?.artist ?: "",
                    color = LightGray,
                    fontSize = 15.sp
                )
            }

            // like
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {

                AppIcon(
                    painter = painterResource(
                        if (uiState.currentMedia?.isFav == true)
                            R.drawable.ic_heart_fill
                        else
                            R.drawable.ic_like
                    ),
                    contentDescription = null,
                    tint = if (uiState.currentMedia?.isFav == true)
                        PurplePrimary
                    else
                        LightGray,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(shape = CircleShape)
                        .clickable {
                            isFav()
                        }
                )
            }


            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}