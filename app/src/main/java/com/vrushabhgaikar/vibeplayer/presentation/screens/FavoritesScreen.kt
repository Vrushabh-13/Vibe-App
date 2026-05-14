package com.vrushabhgaikar.vibeplayer.presentation.screens

import AppTopBar
import AppVideoListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components.SongListItem
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleGradient


@Composable
fun FavoritesScreen(viewModel: HomeViewModel, onBack: () -> Unit) {

    val allMedia = viewModel.allMediaList.collectAsState()

    val favList = allMedia.value.filter {
        it.isFav
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {

        item {
            AppTopBar(
                topBarTitle = "Favourites",
                showBackButton = true,
                onBackClick =   onBack,
                modifier = Modifier.background(PurpleGradient)
            )
        }
        item{ VerticalSpacer(20.dp) }
        items(favList, key = { it.id!! }){media ->
            if(media.mediaType == MediaType.AUDIO){
                SongListItem(
                    media = media,
                    onClick = {},
                    onFavClick = {viewModel.toggleFavorite(media)}
                )
            }else{
                AppVideoListItem(
                    media = media,
                    onClick = {},
                    onIsFavClick = {viewModel.toggleFavorite(media)}
                )
            }

        }

    }

}