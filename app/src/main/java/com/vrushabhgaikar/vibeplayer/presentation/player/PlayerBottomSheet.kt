package com.vrushabhgaikar.vibeplayer.presentation.player

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerBottomSheet(
     uiState: PlayerUiState,
    onDismiss: () -> Unit,
     viewModel: PlayerViewModel,
     homeViewModel: HomeViewModel
){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    if(uiState.isFullPlayerVisible && uiState.currentMedia != null){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            when(uiState.currentMedia?.mediaType){
                MediaType.AUDIO -> {
                    AudioPlayerContent(uiState = uiState,
                        onSeek = { progress ->
                            viewModel.seekTo(progress)
                        },
                        onPlayPause = {
                            viewModel.togglePlayPause()
                        },
                        onForward10 = {
                            viewModel.seekForward10()
                        },
                        onBack10 = {
                            viewModel.seekBackward10()
                        },
                        isFav = {
                             uiState.currentMedia?.let {media ->
                                 val updatedMedia = homeViewModel.toggleFavorite(media)
                                 viewModel.onMediaUpdated(updatedMedia)
                             }
                        },
                        onRepeat = {
                            viewModel.toggleRepeat()
                        }
                    )
                }

                MediaType.VIDEO -> {
                    VideoPlayerContent(uiState)
                }
                else -> {}
            }
        }
    }
}