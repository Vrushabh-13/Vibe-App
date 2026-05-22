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
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun AppSongCard(
    media: MediaItemModel,
    onClick: () -> Unit = {},
    onIsFavClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    showNewChip: Boolean = false

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
                model = media.thumbnailUri ,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onClick),
                placeholderType = if (media.mediaType == MediaType.VIDEO){
                    PlaceholderType.VIDEO}else PlaceholderType.AUDIO
            )
            AppGradientOverlay( modifier = Modifier.matchParentSize())

            if (showNewChip) {

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            Color.Red,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                ) {

                    AppText(
                        text = "NEW",
                        color = White,
                        fontSize = 10.sp
                    )
                }
            }

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
                tint =  if(media.isFav)
                    PurplePrimary
                else
                    LightGray,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .clip(shape = CircleShape)
                    .clickable{
                        onIsFavClick()
                    }
            )
        }


    }

}
