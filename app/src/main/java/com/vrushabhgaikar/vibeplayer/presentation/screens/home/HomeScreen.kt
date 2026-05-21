package com.vrushabhgaikar.vibeplayer.presentation.screens.home

import android.net.Uri
import android.util.Log
import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.components.ContinueCard
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSongCard
import androidx.compose.runtime.collectAsState
import com.google.firebase.messaging.FirebaseMessaging
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.SourceType
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleGradient

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(),
               onSongClick: (MediaItemModel) -> Unit,
               onMediaUpdated: (MediaItemModel) -> Unit = {}){
    val allMedia = viewModel.allMediaList.collectAsState()
    val recentlyPlayed = viewModel.recentlyPlayedList.collectAsState()
    val recommendedSongs = viewModel.recommendedSongs.collectAsState(initial = emptyList())
    val continueListening = viewModel.continueListeningList.collectAsState()
    val favoriteSongs = viewModel.favoriteSongs.collectAsState()

    FirebaseMessaging.getInstance().token
        .addOnCompleteListener { task ->
            if (!task.isSuccessful) return@addOnCompleteListener

            val token = task.result
            Log.d("FCM_TOKEN", token)
        }



    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth - 32.dp) / 3 /* padding adjust */
    LaunchedEffect(Unit) {
        viewModel.loadMedia()
    }
    AppTopBar("Vibe",
        modifier = Modifier.background(PurpleGradient))
//    Spacer(modifier = Modifier.height(12.dp))
    LazyColumn (
        modifier = Modifier
            .padding(top = 60.dp)
            .background(BlackBg),
        contentPadding = PaddingValues(bottom = 30.dp)
    ) {
//        item {
//            HomeTopBar()
//            Spacer(modifier = Modifier.height(8.dp))
//            FilterChips()
//            Spacer(modifier = Modifier.height(12.dp))

//        }
        if(recentlyPlayed.value.isNotEmpty()){
        item { AppSectionTitle(stringResource(R.string.recently_played)) }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(recentlyPlayed.value) { media ->
                    AppSongCard(
                        media = media,
                        modifier = Modifier.width(itemWidth),
                        onClick = {
                            viewModel.updatePlayedMedia(media)
                            viewModel.markRecommendationViewed(
                                media.id ?: 0L
                            )
                            onSongClick(media)
                        },
                        onIsFavClick = {
                            val updatedMedia = viewModel.toggleFavorite(media)
                            onMediaUpdated(updatedMedia)

                        }
                    )
                }
            }
        }
        }

        item {
            VerticalSpacer(16.dp)
        }
        item {  AppSectionTitle(stringResource(R.string.recommended_songs),) }
        item {  LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(recommendedSongs.value) {song ->

                val mediaFromState =
                    allMedia.value.find { it.id == song.id }
                val media = mediaFromState ?: MediaItemModel(
                    id = song.id,
                    title = song.title,
                    artist = song.artist,
                    uri = Uri.parse(song.songUrl),
                    thumbnailUri = Uri.parse(song.thumbnailUrl),
                    mediaType = MediaType.AUDIO,
                    sourceType = SourceType.ONLINE,
                    isFav = song.isFav,
                    isNewRecommendation = song.isNewRecommendation
                )
                LaunchedEffect(media.id) {
                    viewModel.syncRecommendedSong(media)
                }
                AppSongCard(
                    media = media,
                    showNewChip = media.isNewRecommendation,
                    modifier = Modifier.width(itemWidth),
                    onClick = {
                        viewModel.updatePlayedMedia(media)
                        viewModel.markRecommendationViewed(
                            media.id ?: 0L
                        )
                        onSongClick(media)
                    },
                    onIsFavClick = {
                        val updated = viewModel.toggleFavorite(media)
                        onMediaUpdated(updated)
                    }
                )
            }
        } }
       item{VerticalSpacer(16.dp)}
        item { AppSectionTitle(stringResource(R.string.continue_listening_watching),) }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(continueListening.value) {media ->
                    ContinueCard(
                        media = media,
                        onPlayClick  = {
                            viewModel.updatePlayedMedia(media)
                            onSongClick(media)
                        }
                    )
                }
            }
        }

    }

}
