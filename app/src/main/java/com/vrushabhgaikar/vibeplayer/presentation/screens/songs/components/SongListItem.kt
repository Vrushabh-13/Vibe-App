package com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.HorizontalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun SongListItem(
    song: Song
){
    Column {

    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(
            painter = painterResource(song.image),
            contentDescription = null,
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(8.dp))

        )
        HorizontalSpacer(12.dp)

        Column(modifier = Modifier.weight(1f)) {

            AppText(song.title, color = White, fontSize = 15.sp, fontWeight = FontWeight.Medium)

            AppText(song.artist, color = LightGray, fontSize = 12.sp)
        }
        AppIcon(
            painter = painterResource(id = com.vrushabhgaikar.vibeplayer.R.drawable.ic_like),
            null,
            tint = PurplePrimary,
            modifier = Modifier.size(22.dp)
        )
        HorizontalSpacer(12.dp)

        AppIcon(
            painter = painterResource(id = com.vrushabhgaikar.vibeplayer.R.drawable.ic_play),
            contentDescription = null,
            tint = White,
            modifier = Modifier
                .size(40.dp)
                .background(
                    Color.Black.copy(alpha = 0.5f),
                    CircleShape
                )
                .padding(8.dp)
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(start = 80.dp),
        thickness = 0.5.dp,
        color = LightGray.copy(alpha = 0.1f)
    )
}