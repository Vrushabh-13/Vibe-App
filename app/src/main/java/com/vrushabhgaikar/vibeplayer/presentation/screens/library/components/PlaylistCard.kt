package com.vrushabhgaikar.vibeplayer.presentation.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun PlaylistCard(
    name: String,
    songCount: Int,
    onClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
            .padding(16.dp)
    ) {
        AppImage(
            painter = painterResource(R.drawable.img_playlist_folder),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .size(120.dp)
        )
        Row() {
            Column() {
                AppText(
                    text = name,
                    color = White
                )
                AppText(
                    text = "$songCount Songs",
                    color = White
                )
            }
            AppImage(
                painter = painterResource(id = R.drawable.img_edit_button),
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 30.dp, y = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onEditClick()
                    },
                contentDescription = stringResource(R.string.edit_button),
                contentScale = ContentScale.Crop

            )

        }

        VerticalSpacer(8.dp)
        
    }
}