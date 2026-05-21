package com.vrushabhgaikar.vibeplayer.presentation.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components.SongListItem
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleGradient

@Composable
fun HistoryScreen(
    viewModel: HomeViewModel,
    onSongClick: (MediaItemModel) -> Unit,
    onBack: () -> Unit,
    onMediaUpdated: (MediaItemModel) -> Unit
){
    val historyList = viewModel.historyList.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg)
    ){
        AppTopBar(
            topBarTitle = "History",
            showBackButton = true,
            onBackClick = onBack,
            modifier = Modifier.background(PurpleGradient)
        )

        LazyColumn( modifier = Modifier,
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 12.dp
            )
        )
        {
            items(historyList.value){media ->
                SongListItem(
                    media = media,
                    onClick = { viewModel.updatePlayedMedia(media)
                        onSongClick(media)},
                    onFavClick = {
                        val updatedMedia = viewModel.toggleFavorite(media)
                        onMediaUpdated(updatedMedia)
                    }

                )
                VerticalSpacer(8.dp)
            }


        }
    }



}