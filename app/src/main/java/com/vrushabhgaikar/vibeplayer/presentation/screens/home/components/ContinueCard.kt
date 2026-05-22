package com.vrushabhgaikar.vibeplayer.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType
import com.vrushabhgaikar.vibeplayer.presentation.components.AppGradientOverlay
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun ContinueCard(
    media: MediaItemModel,
    onPlayClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .width(200.dp)
            .background(CardBg, RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {

        // 🔹 Top Section (Image + Chip)
        Box(
            modifier = Modifier
                .border(
                    width = 0.1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(12)
                )
        ) {

            AppImage(
                model = media.thumbnailUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onPlayClick() },
                placeholderType = if (media.mediaType == MediaType.VIDEO) {
                    PlaceholderType.VIDEO
                } else PlaceholderType.AUDIO

            )
            AppGradientOverlay(modifier = Modifier.matchParentSize())

            // 🔹 Audio/Video Chip
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(7.dp)
                    .size(30.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    painter = painterResource(
                        id =
                            if (media.mediaType == MediaType.AUDIO)
                                R.drawable.img_music_icon
                            else
                                R.drawable.img_video_icon
                    ),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(14.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Middle Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = media.title ?: "",
                    color = White,
                    fontSize = 14.sp,
                    maxLines = 1
                )

                Text(
                    text = media.artist ?: "",
                    color = LightGray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }


        }

    }
}

