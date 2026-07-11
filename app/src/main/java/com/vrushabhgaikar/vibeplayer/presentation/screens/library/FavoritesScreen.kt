package com.vrushabhgaikar.vibeplayer.presentation.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.components.AppEmptyState
import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.components.SongListItem
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleGradient


@Composable
fun FavoritesScreen(
    viewModel: HomeViewModel,
    onSongClick: (MediaItemModel) -> Unit,
    onBack: () -> Unit,
    onMediaUpdated: (MediaItemModel) -> Unit
) {

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
                onBackClick = onBack,
                modifier = Modifier.background(PurpleGradient)
            )
        }
        item { VerticalSpacer(20.dp) }
        if(
            favList.isEmpty()
        ){
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AppEmptyState(
                        title = "No Favorites Yet",
                        description = "Songs and Videos you like will appear here"
                    )
                }
            }
        }else{
            items(favList, key = { it.id!! }) { media ->
                SongListItem(
                    media = media,
                    onClick = {
                        viewModel.updatePlayedMedia(media)
                        onSongClick(media)
                    },
//                    onFavClick = { viewModel.toggleFavorite(media) }
                    onFavClick = {
                        val updatedMedia = viewModel.toggleFavorite(media)
                        onMediaUpdated(updatedMedia)
                    }
                )
            }

        }
    }


}