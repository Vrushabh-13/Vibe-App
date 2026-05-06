package com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun SongCard(
    image: Int,
    title: String,
    artist: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier
            .clickable{onClick()}
    ) {
        Box{
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(36.dp)
                    .background(
                        Color.Black.copy(alpha = 0.0f),
                        shape = CircleShape
                    )
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row() {
            Column() {
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

            Icon(painter = painterResource(id = R.drawable.ic_like),contentDescription = null,
                tint = White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }


    }

}
