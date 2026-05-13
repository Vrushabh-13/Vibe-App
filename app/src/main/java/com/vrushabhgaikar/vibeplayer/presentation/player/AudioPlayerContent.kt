package com.vrushabhgaikar.vibeplayer.presentation.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleLight
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White
import com.vrushabhgaikar.vibeplayer.utils.TimeUtils

@Composable
fun AudioPlayerContent(
    uiState: PlayerUiState,
    onSeek: (Float) -> Unit,
    onPlayPause: () -> Unit,
    onForward10: () -> Unit,
    onBack10: () -> Unit,
    isFav: () -> Unit,
    onRepeat: () -> Unit

) {
    val progress = if (uiState.duration > 0){
        uiState.currentPosition.toFloat()/uiState.duration
    }else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(24.dp)

        // Album Art + Pulse
        AppImage(
            model = uiState.currentMedia?.thumbnailUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(8.dp))
        )


        VerticalSpacer(32.dp)

        // Song Info
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
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                VerticalSpacer(4.dp)

                AppText(
                    text = uiState.currentMedia?.artist?: "",
                    color = LightGray,
                    fontSize = 18.sp
                )
            }

        }

        VerticalSpacer(24.dp)

        // Slider
        Slider(
            value = progress,
            onValueChange = { newValue ->
                onSeek(newValue)
            },
            colors = SliderDefaults.colors(
                thumbColor = PurplePrimary,
                activeTrackColor = PurplePrimary,
                inactiveTrackColor = LightGray.copy(alpha = 0.3f)
            )
        )

        // Time
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AppText(
                text = TimeUtils.formatDuration(uiState.currentPosition),
                color = LightGray
            )

            AppText(
                text = TimeUtils.formatDuration(uiState.duration),
                color = LightGray
            )
        }

        VerticalSpacer(36.dp)

        // Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AppIcon(
                painter = painterResource(id = R.drawable.ic_repeat),
                contentDescription = null,
                tint =  if (uiState.isRepeatEnabled)
                    PurplePrimary
                else
                    LightGray,
                modifier = Modifier.size(34.dp)
                    .clickable{
                        onRepeat()
                    }
            )

            AppIcon(
                painter = painterResource(id = R.drawable.ic_10_back),
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(42.dp)
                    .clickable{ onBack10() }
            )

            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                PurpleLight,
                                PurplePrimary
                            )
                        ),
                        shape = CircleShape
                    )
                    .clickable{
                            onPlayPause()
                    },
                contentAlignment = Alignment.Center
            ) {

                AppIcon(
                    painter =
                        if (uiState.isPlaying)
                            painterResource(id = R.drawable.ic_pause)
                        else
                            painterResource(id = R.drawable.ic_play),

                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(52.dp)
                )
            }

            AppIcon(
                painter = painterResource(id = R.drawable.ic_10_forward),
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(42.dp)
                    .clickable{
                        onForward10()
                    }
            )

            AppIcon(
                painter = painterResource(if(uiState.currentMedia?.isFav == true)
                    R.drawable.ic_heart_fill
                else
                    R.drawable.ic_like),
                contentDescription = null,
                tint = if(uiState.currentMedia?.isFav == true)
                    PurplePrimary
                    else
                        LightGray,
                modifier = Modifier.size(38.dp)
                    .clickable{
                        isFav()
                    }
            )
        }

       VerticalSpacer(30.dp)
    }
}