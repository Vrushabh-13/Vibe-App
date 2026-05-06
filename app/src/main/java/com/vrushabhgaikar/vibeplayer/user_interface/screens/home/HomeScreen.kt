package com.vrushabhgaikar.vibeplayer.user_interface.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components.ContinueCard
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components.FilterChips
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components.HomeTopBar
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components.SectionTitle
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components.SongCard
import com.vrushabhgaikar.vibeplayer.user_interface.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val songList = listOf(
        Song(R.drawable.ic_songs, "Kesariya", "Arijit Singh"),
        Song(R.drawable.ic_songs, "Blinding Lights", "The Weeknd"),
        Song(R.drawable.ic_songs, "Shape of You", "Ed Sheeran"),
        Song(R.drawable.ic_songs, "Blinding Lights", "The Weeknd"),
        Song(R.drawable.ic_songs, "Shape of You", "Ed Sheeran")
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth - 32.dp) / 3 /* padding adjust */
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg)
    ) {
        item {
            HomeTopBar()
            Spacer(modifier = Modifier.height(8.dp))
            FilterChips()
            Spacer(modifier = Modifier.height(12.dp))

        }
        item { SectionTitle("Recently Played") }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(songList) { song ->
                    SongCard(
                        image = song.image,
                        title = song.title,
                        artist = song.artist,
                        modifier = Modifier.width(itemWidth)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {  SectionTitle("Recommended Songs") }
        item {  LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(songList) { song ->
                SongCard(
                    image = song.image,
                    title = song.title,
                    artist = song.artist,
                    modifier = Modifier.width(itemWidth)
                )
            }
        } }
       item{Spacer(modifier = Modifier.height(16.dp))}
        item { SectionTitle("Continue Listening") }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(songList) {
                    ContinueCard(
                        image = it.image,
                        title = it.title,
                        subtitle = it.artist,
                        isVideo = false
                    )
                }
            }
        }

    }

}
