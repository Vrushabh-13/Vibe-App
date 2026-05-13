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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.data.local.MediaStoreReader
import com.vrushabhgaikar.vibeplayer.data.model.Song
import com.vrushabhgaikar.vibeplayer.presentation.components.AppFilterChips
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSearchBar
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSongCard
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components.SongListItem
import kotlin.collections.arrayListOf

@Composable
fun SongsScreen() {
    val context = LocalContext.current
    val reader = MediaStoreReader(context)
    val audio = reader.getLocalAudio()

    val songList = remember{
        arrayListOf(
            Song(R.drawable.img_music_thumb, "On My Way", "Alan Walker"),
            Song(R.drawable.song2, "Midnight Drive", "Arijit Singh"),
            Song(R.drawable.img_music_thumb, "Heeriye", "Jasleen Royal"),
            Song(R.drawable.song2, "Ranjha", "Shershaah"),
            Song(R.drawable.img_music_thumb, "Humnava Mere", "Jubin Nautiyal")
        )
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item{AppTopBar("Songs")}

        item{ VerticalSpacer(12.dp) }

        item { AppSearchBar() }

        item { VerticalSpacer(16.dp) }

        item { AppSectionTitle(stringResource(R.string.trending_now)) }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(audio) { media ->
                    AppSongCard(
                        media = media
                    )
                }
            }
        }

        item { VerticalSpacer(16.dp)}

        item { AppFilterChips() }

        item { VerticalSpacer(16.dp) }

//        item { SectionTitle("") }

        items(songList) { song ->
            SongListItem(song = song)
        }

//        item { Spacer(modifier = Modifier.height(16.dp)) }

//        item { SectionTitle("Recommendations") }

        items(songList) { song ->
            SongListItem(song = song)
        }


    }
}