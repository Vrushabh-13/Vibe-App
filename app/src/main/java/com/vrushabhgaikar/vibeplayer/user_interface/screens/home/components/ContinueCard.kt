package com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun ContinueCard(
    image: Int,
    title: String,
    subtitle: String,
    isVideo: Boolean,
    onPlayClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .width(200.dp)
            .background(CardBg, RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {

        // 🔹 Top Section (Image + Chip)
        Box {

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            // 🔹 Audio/Video Chip
            Text(
                text = if (isVideo) "VIDEO" else "AUDIO",
                color = White,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(6.dp)
                    .background(
                        color = PurplePrimary,
                        //Color.PurplePrimary.copy(alpha = 0.6f),
                        RoundedCornerShape(50)
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            )
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
                    text = title,
                    color = White,
                    fontSize = 14.sp,
                    maxLines = 1
                )

                Text(
                    text = subtitle,
                    color = LightGray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            // 🔹 Play Button
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onPlayClick() }
                    .background(
                        Color.Black.copy(alpha = 0.6f),
                        CircleShape
                    )
                    .padding(6.dp)
            )
        }

    }
}

