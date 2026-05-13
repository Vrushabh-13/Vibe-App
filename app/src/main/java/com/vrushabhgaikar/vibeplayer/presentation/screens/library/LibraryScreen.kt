package com.vrushabhgaikar.vibeplayer.presentation.screens.library

import AppTopBar
import AppVideoListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.components.QuickActionCard
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSearchBar
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.components.PlaylistCard

@Composable
fun LibraryScreen(){
    val playlistList = remember{
        arrayListOf(
            Song(R.drawable.song2, "Night Drive", "24 songs"),
            Song(R.drawable.img_music_thumb, "Chill Vibes", "18 songs"),
            Song(R.drawable.song2, "Workout Mix", "20 songs")
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item{AppTopBar("Library")
             }

        item { VerticalSpacer(12.dp) }

        item { AppSearchBar() }

        item { VerticalSpacer(16.dp) }

        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.ic_like) ,
                    title = stringResource(R.string.favourites),
                    subtitle = "128 items"
                )

                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.ic_playlist),
                    title = stringResource(R.string.playlists),
                    subtitle = "12 playlists",
                )

                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.ic_download),
                    title = stringResource(R.string.downloads),
                    subtitle = "86 items",
                )

                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.ic_recent),
                    title = stringResource(R.string.recent),
                    subtitle = "36 items")
            }
        }
        item { VerticalSpacer(20.dp) }

        item { AppSectionTitle(stringResource(R.string.my_playlists)) }

        item{
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(playlistList){
                    PlaylistCard(it)
                }
            }
        }

        item{VerticalSpacer(20.dp)}

        item{AppSectionTitle(stringResource(R.string.my_videos))}

        items(playlistList){
            AppVideoListItem(it)

        }

    }
}