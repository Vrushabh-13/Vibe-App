package com.vrushabhgaikar.vibeplayer.presentation.screens.library

import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.components.SongSelectionItem
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components.SongListItem
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg



@Composable
fun PlaylistDetailsScreen(
    viewModel: HomeViewModel,
    playlistName: String,
    onBack: () -> Unit,
    onSongClick: (MediaItemModel) -> Unit,
    onMediaUpdated: (MediaItemModel) -> Unit
) {

    val allMedia =
        viewModel.allMediaList.collectAsState().value

    val playlist = viewModel.playlists.collectAsState().value
        .find { it.name == playlistName }

    val playlistSongs = allMedia.filter {
        playlist?.mediaIds?.contains(it.id) == true
    }
    val selectedSongs =
        remember {
            mutableStateListOf<Long>()
        }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg)
    ) {

        AppTopBar(
            topBarTitle = playlistName,
            showBackButton = true,
            onBackClick = onBack
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(playlistSongs) { media ->

                SongListItem(
                    media = media,
                    onClick = {
                        onSongClick(media)
                    },
                    onFavClick = {
                        val updated = viewModel.toggleFavorite(media)
                        onMediaUpdated(updated)
                    }
                )
            }
        }

    }
}


//@Composable
//fun PlaylistDetailsScreen(
//    viewModel: HomeViewModel,
//    playlistName: String,
//    onBack: () -> Unit
//) {
//
//    val mediaList = viewModel.allMediaList.collectAsState().value
//
//    val selectedSongs = remember {
//        mutableStateListOf<Long>()
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BlackBg)
//    ) {
//
//        AppTopBar(
//            topBarTitle = playlistName,
//            showBackButton = true,
//            onBackClick = onBack
//        )
//
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//
//            items(mediaList) { media ->
//
//                SongSelectionItem(
//                    media = media,
//                    isSelected = selectedSongs.contains(media.id),
//
//                    onCheckedChange = { checked ->
//
//                        media.id?.let { id ->
//
//                            if (checked) {
//                                selectedSongs.add(id)
//                            } else {
//                                selectedSongs.remove(id)
//                            }
//                        }
//                    }
//                )
//            }
//        }
//    }
//}