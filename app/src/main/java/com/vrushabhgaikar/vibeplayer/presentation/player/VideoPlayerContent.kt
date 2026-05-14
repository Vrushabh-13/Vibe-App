package com.vrushabhgaikar.vibeplayer.presentation.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.ui.PlayerView
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun VideoPlayerContent(
    uiState: PlayerUiState
) {

//    AndroidView(
//        factory = {context ->
//            PlayerView(context).apply {
//                player = viewModel { }
//            }
//        }
//    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Drag Handle
//        Box(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .width(50.dp)
//                .height(5.dp)
//                .background(
//                    LightGray.copy(alpha = 0.4f),
//                    RoundedCornerShape(50)
//                )
//        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Back button
//        Icon(
//            painterResource(id = R.drawable.ic_left_arrow),
//            contentDescription = null,
//            tint = White,
//            modifier = Modifier.size(28.dp)
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))

        // ===================================================
        // VIDEO AREA
        // ===================================================

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
            ){
                AppImage(
                    model = uiState.currentMedia?.thumbnailUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    placeholderType = PlaceholderType.VIDEO
                )
            }


            // 🔹 Dark overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
            )

            // 🔹 More menu
            Icon(
                painterResource(id = R.drawable.ic_menu),
                null,
                tint = White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(28.dp)
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
                    modifier = Modifier.size(44.dp)
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
                        modifier = Modifier.size(48.dp)
                    )
                }

                // forward
                Icon(
                    painterResource(id = R.drawable.ic_10_forward),
                    null,
                    tint = White,
                    modifier = Modifier.size(44.dp)
                )
            }

            // ===================================================
            // BOTTOM CONTROLS
            // ===================================================

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                Slider(
                    value = 0.4f,
                    onValueChange = {},
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
                        "01:24",
                        color = White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "04:12",
                        color = White
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Icon(
                        painterResource(id = R.drawable.ic_fullscreen),
                        null,
                        tint = White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ===================================================
        // VIDEO INFO
        // ===================================================

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
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = uiState.currentMedia?.artist ?: "",
                    color = LightGray,
                    fontSize = 18.sp
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

                Icon(
                    painterResource(id = R.drawable.ic_like),
                    null,
                    tint = PurplePrimary
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // download
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painterResource(id = R.drawable.ic_download),
                    null,
                    tint = White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ===================================================
        // DESCRIPTION CARD
        // ===================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.background,
                    RoundedCornerShape(20.dp)
                )
                .padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painterResource(id = R.drawable.ic_calender),
                    null,
                    tint = LightGray,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "Premiered on 12 May 2024",
                    color = LightGray
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text =
                    "Presenting Midnight Live by Arijit Singh. " +
                            "A soul-stirring live performance full of emotions and memories.",

                color = White.copy(alpha = 0.85f),

                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Show more",
                color = PurplePrimary,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}