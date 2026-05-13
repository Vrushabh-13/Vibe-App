package com.vrushabhgaikar.vibeplayer.presentation.screens.video.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
fun VideoCard(video: Song){
    Column(modifier = Modifier.width(220.dp)
        .clickable(onClick = {
            // open bottomsheet
        })) {
        Box(
            modifier = Modifier
                .border(
                    width = 0.1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(14)
                )
        ){
            AppImage(
                painter = painterResource(video.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

            AppGradientOverlay(modifier = Modifier.matchParentSize())

            AppIcon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
                    .background(
                        Color.Black.copy(alpha = 0.6f),
                        CircleShape
                    )
                    .padding(6.dp)
            )
            AppText(
                text = "03:45",
                color = White,
                fontSize = 11.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp)
                    .background(Color.Black.copy(0.7f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)

            )
        }

        VerticalSpacer(8.dp)

                AppText(video.title, color = White, fontSize = 14.sp, maxLines = 1)
                AppText(video.artist, color = LightGray, fontSize = 12.sp)


    }
}