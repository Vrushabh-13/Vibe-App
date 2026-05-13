package com.vrushabhgaikar.vibeplayer.presentation.screens.video

import AppTopBar
import AppVideoListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.presentation.components.AppFilterChips
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSearchBar
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.video.components.VideoCard

@Composable
fun VideoScreen(){
    val videoList = remember {
        listOf(
            Song(R.drawable.img_music_thumb, "Humnava Mere", "Jubin Nautiyal"),
            Song(R.drawable.song2, "Channa Mereya", "Pritam"),
            Song(R.drawable.img_music_thumb, "Ranjha", "Shershaah")
        )
    }

    LazyColumn( modifier = Modifier
        .fillMaxSize()
        .background(BlackBg),
        contentPadding = PaddingValues(bottom = 80.dp)) {

        item {AppTopBar("Video")
        }

        item { VerticalSpacer(12.dp) }

        item { AppSearchBar() }

        item { VerticalSpacer(16.dp) }

        item { AppSectionTitle("Trending Now") }

        item{
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(videoList){video ->
                    VideoCard(
                        video
                    )
                }
            }
        }

        item { VerticalSpacer(16.dp) }

        item { AppFilterChips() }

        items(videoList) { video ->
            AppVideoListItem(video)
        }
    }
}