package com.vrushabhgaikar.vibeplayer.presentation.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.components.SongSelectionItem
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import  androidx.compose.foundation.lazy.items

@Composable
fun PlaylistSelectionScreen(
    viewModel: HomeViewModel,
    playlistName: String,
    onBack: () -> Unit
) {

    val allMedia = viewModel.allMediaList.collectAsState().value
    val playlists = viewModel.playlists.collectAsState().value

    val playlist = playlists.find { it.name == playlistName }

    val selectedSongs = remember {
        mutableStateListOf<Long>().apply {
            playlist?.mediaIds?.let { addAll(it) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg)
    ) {

        // TOP BAR
        AppTopBar(
            topBarTitle = "Edit: $playlistName",
            showBackButton = true,
            onBackClick = onBack
        )

        // SONG LIST
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(allMedia) { media ->

                SongSelectionItem(
                    media = media,
                    isSelected = selectedSongs.contains(media.id),
                    onCheckedChange = { checked ->

                        media.id?.let { id ->
                            if (checked) {
                                if (!selectedSongs.contains(id)) {
                                    selectedSongs.add(id)
                                }
                            } else {
                                selectedSongs.remove(id)
                            }
                        }
                    }
                )
            }
        }

        // SAVE BUTTON
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.saveSongsToPlaylist(
                    playlistName,
                    selectedSongs.toList()
                )
                onBack()
            }
        ) {
            AppText("Save Playlist")
        }
    }
}