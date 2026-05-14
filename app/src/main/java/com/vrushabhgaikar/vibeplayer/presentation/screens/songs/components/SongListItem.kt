package com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType
import com.vrushabhgaikar.vibeplayer.presentation.components.AppGradientOverlay
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.HorizontalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun SongListItem(
    media: MediaItemModel,
    onClick: () -> Unit,
    onFavClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .background(
                Color.DarkGray.copy(alpha = 0.25f),
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(14.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) { Box(
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
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),

                placeholderType = PlaceholderType.AUDIO

            )
            AppGradientOverlay( modifier = Modifier.matchParentSize())
        }

            HorizontalSpacer(12.dp)

            Column(modifier = Modifier.weight(1f)) {

                AppText(media.title?:"", color = White, fontSize = 15.sp, fontWeight = FontWeight.Medium
                ,maxLines = 1)

                AppText(media.artist?:"", color = LightGray, fontSize = 12.sp,maxLines = 1)
            }
            HorizontalSpacer(30.dp)
            AppIcon(
                painter = painterResource(id = if(media.isFav) {
                    com.vrushabhgaikar.vibeplayer.R.drawable.ic_heart_fill
                }
                else{
                    com.vrushabhgaikar.vibeplayer.R.drawable.ic_like}),
                null,
                tint =  if(media.isFav)
                    PurplePrimary
                else
                    LightGray,
                modifier = Modifier.size(22.dp)
                    .clip(shape = CircleShape)
                    .clickable{
                        onFavClick()
                    }
            )
//            HorizontalSpacer(12.dp)

//            AppIcon(
//                painter = painterResource(id = com.vrushabhgaikar.vibeplayer.R.drawable.ic_play),
//                contentDescription = null,
//                tint = White,
//                modifier = Modifier
//                    .size(42.dp)
//                    .background(
//                        Color.Black.copy(alpha = 0.5f),
//                        CircleShape
//                    )
//                    .padding(10.dp)
//            )
        }
//        HorizontalDivider(
//            modifier = Modifier.padding(start = 80.dp),
//            thickness = 0.5.dp,
//            color = LightGray.copy(alpha = 0.1f)
//        )
    }

}