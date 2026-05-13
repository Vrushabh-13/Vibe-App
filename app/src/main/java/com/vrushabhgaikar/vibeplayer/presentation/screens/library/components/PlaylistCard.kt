package com.vrushabhgaikar.vibeplayer.presentation.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.presentation.components.AppGradientOverlay
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun PlaylistCard(song: Song){
    Column() {
        Box(
            modifier = Modifier
                .border(
                    width = 0.1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(16)
                )
        ) {
            AppImage(
                painter = painterResource(song.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            AppGradientOverlay(modifier = Modifier.matchParentSize())
            AppIcon(
                 painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        CircleShape
                    )
                    .padding(6.dp)
            )
        }
        VerticalSpacer(6.dp)
        AppText(song.title, color = White, fontSize = 13.sp)
        AppText(song.artist, color = LightGray, fontSize = 11.sp)
    }
}