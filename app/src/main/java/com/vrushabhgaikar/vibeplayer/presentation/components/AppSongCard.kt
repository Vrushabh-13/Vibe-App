package com.vrushabhgaikar.vibeplayer.presentation.components



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun AppSongCard(
    media: MediaItemModel,
    onClick: () -> Unit = {},
    onIsFavClick: () -> Unit = {},
    modifier: Modifier = Modifier,

    ){
    Column(
        modifier = modifier
            .width(120.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 0.1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(16)
                )
        ){
            AppImage(
                model = media.thumbnailUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.img_music_thumb),
                error = painterResource(R.drawable.img_music_thumb),
                fallback = painterResource(R.drawable.img_music_thumb),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onClick)
            )
            AppGradientOverlay( modifier = Modifier.matchParentSize())
            AppIcon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(36.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(6.dp)
            )
        }
        VerticalSpacer(8.dp)

        Row() {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                AppText(
                    text = media.title?:"",
                    color = White,
                    fontSize = 14.sp,
                    maxLines = 1
                )

                AppText(
                    text = media.artist?:"",
                    color = LightGray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            AppIcon(painter = painterResource(
                id =
                    if(media.isFav)
                        R.drawable.ic_heart_fill
                    else
                        R.drawable.ic_like
            ),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .clickable{
                        onIsFavClick()
                    }
            )
        }


    }

}
