package com.vrushabhgaikar.vibeplayer.presentation.screens.songs

import AppTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.components.AppFilterChips
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSearchBar
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSongCard
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components.SongListItem

@Composable
fun SongsScreen(viewModel: HomeViewModel,
                onSongClick: (MediaItemModel) -> Unit,
                onMediaUpdated: (MediaItemModel) -> Unit) {
    val trendingSongs = viewModel.recommendedSongs.collectAsState()
    val songs = viewModel.filteredSongs.collectAsState()
    val selectedFilter = viewModel.songFilter.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()
//    val context = LocalContext.current
//    val reader = MediaStoreReader(context)
//    val audio = reader.getLocalAudio()

//    val songList = remember{
//        arrayListOf(
//            Song(R.drawable.img_music_thumb, "On My Way", "Alan Walker"),
//            Song(R.drawable.song2, "Midnight Drive", "Arijit Singh"),
//            Song(R.drawable.img_music_thumb, "Heeriye", "Jasleen Royal"),
//            Song(R.drawable.song2, "Ranjha", "Shershaah"),
//            Song(R.drawable.img_music_thumb, "Humnava Mere", "Jubin Nautiyal")
//        )
//    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item{AppTopBar("Songs")}

        item{ VerticalSpacer(12.dp) }

        item { AppSearchBar(
            value = searchQuery.value,
            onValueChange = {
                viewModel.updateSearchQuery(it)
            }
        ) }

        item { VerticalSpacer(16.dp) }

        if(searchQuery.value.isBlank()){
            item { AppSectionTitle(stringResource(R.string.trending_now)) }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(trendingSongs.value) { media ->
                        AppSongCard(
                            media = media,
                            onClick = {
                                onSongClick(media)
                            },
                            onIsFavClick = {
                                val updated = viewModel.toggleFavorite(media)
                                onMediaUpdated(updated)

                            }
                        )
                    }
                }
            }

            item { VerticalSpacer(16.dp)}
        }



        item { AppFilterChips(
            selectedFilter = selectedFilter.value,
            onSelect ={viewModel.setSongFilter(it)}
        ) }

        item { VerticalSpacer(16.dp) }

//        item { SectionTitle("") }

        items(items = songs.value, key = { it.id!! }) { media ->
            SongListItem(media = media,
                onClick = { onSongClick(media) },
                onFavClick = {
                    val updated =
                        viewModel.toggleFavorite(media)
                    onMediaUpdated(updated)
                })

        }

//        item { Spacer(modifier = Modifier.height(16.dp)) }

//        item { SectionTitle("Recommendations") }

//        items(songList) { song ->
//            SongListItem(song = song)
//        }


    }
}