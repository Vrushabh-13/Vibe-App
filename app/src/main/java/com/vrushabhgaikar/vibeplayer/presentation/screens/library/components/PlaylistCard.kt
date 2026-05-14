package com.vrushabhgaikar.vibeplayer.presentation.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType
import com.vrushabhgaikar.vibeplayer.presentation.components.AppGradientOverlay
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun PlaylistCard(media: MediaItemModel){
    Column(modifier = Modifier.width(140.dp)) {
        Box(
            modifier = Modifier
                .border(
                    width = 0.1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(16)
                )
        ) {
            AppImage(
                model = media.thumbnailUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(16.dp)),
                placeholderType = PlaceholderType.VIDEO

            )
            AppGradientOverlay(modifier = Modifier.matchParentSize())
            AppIcon(
                 painter = painterResource(id =
                     if(media.mediaType == MediaType.AUDIO)
                         R.drawable.img_music_icon
                     else
                         R.drawable.img_video_icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(43.dp)
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        CircleShape
                    )
                    .padding(7.dp)
            )
        }
        VerticalSpacer(6.dp)
        AppText(media.title?:"", color = White, fontSize = 13.sp , maxLines = 1, overflow = TextOverflow.Ellipsis )
        AppText(media.artist?:"", color = LightGray, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis )
    }
}