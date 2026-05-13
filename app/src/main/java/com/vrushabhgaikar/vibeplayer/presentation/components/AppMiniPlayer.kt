package com.vrushabhgaikar.vibeplayer.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.R.drawable.img_music_thumb
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun AppMiniPlayer(
    media : MediaItemModel,
    image: Uri? = null,
    title: String,
    artist: String,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit = {},
    onLikeClick: () -> Unit = {},
    onPlayerClick: () -> Unit = {}
) {
    Card(modifier = Modifier.fillMaxWidth()
        .padding(10.dp),
        RoundedCornerShape(15.dp)){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBg)
                .clickable { onPlayerClick() } // full bar clickable
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 🔹 Left: Image
            AppImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(10.dp))

            // 🔹 Middle: Title + Artist
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = White,
                    fontSize = 14.sp,
                    maxLines = 1
                )

                Text(
                    text = artist,
                    color = LightGray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            // 🔹 Right: Like Button
            AppIcon(
                painter = painterResource( if(media.isFav)
                    R.drawable.ic_heart_fill
                else
                    R.drawable.ic_like),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(22.dp)
                    .clickable { onLikeClick() }
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 🔹 Play / Pause Button
            AppIcon(
                painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onPlayPauseClick() }
            )
        }
    }

}