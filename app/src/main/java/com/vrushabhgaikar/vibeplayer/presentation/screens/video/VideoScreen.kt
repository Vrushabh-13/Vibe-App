package com.vrushabhgaikar.vibeplayer.presentation.screens.video

import AppVideoListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.components.AppEmptyState
import com.vrushabhgaikar.vibeplayer.presentation.components.AppFilterChips
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSearchBar
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.video.components.VideoCard
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg

@Composable
fun VideoScreen(
    viewModel: HomeViewModel,
    onVideoClick: (MediaItemModel) -> Unit,
    onMediaUpdated: (MediaItemModel) -> Unit
) {
    val trendingVideos = viewModel.trendingVideos.collectAsState()
    val videos = viewModel.filteredVideos.collectAsState()
    val selectedFilter =
        viewModel.videoFilter.collectAsState()
    val searchQuery =
        viewModel.videoSearchQuery.collectAsState()

    AppTopBar("Video")
    LazyColumn(
        modifier = Modifier
            .padding(top = 60.dp)
            .background(BlackBg),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item { VerticalSpacer(12.dp) }

        item {
            AppSearchBar(
                value = searchQuery.value,
                onValueChange = {
                    viewModel.updateVideoSearchQuery(it)
                })
        }

        item { VerticalSpacer(16.dp) }
        if (searchQuery.value.isBlank()) {
            item { AppSectionTitle("Trending Now") }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(trendingVideos.value) { video ->
                        VideoCard(
                            media = video,
                            onClick = {
                                viewModel.updatePlayedMedia(video)
                                onVideoClick(video)
                            }
                        )
                    }
                }
            }

            item { VerticalSpacer(16.dp) }
        }

        item {
            AppFilterChips(
                selectedFilter = selectedFilter.value,
                onSelect = {
                    viewModel.setVideoFilter(it)
                }
            )
        }
        item { VerticalSpacer(16.dp) }
        if(searchQuery.value.isNotBlank() &&
            videos.value.isEmpty()){
            item {
                AppEmptyState(
                    title = "Not Found",
                    description = "Please Search with proper keyword"
                ) }
        }
        items(items = videos.value, key = { it.id!! }) { media ->
            AppVideoListItem(
                media = media,
                onClick = {
                    viewModel.updatePlayedMedia(media)
                    onVideoClick(media)
                },
                onIsFavClick = {
                    val updated = viewModel.toggleFavorite(media)
                    onMediaUpdated(updated)

                })
        }
    }
}