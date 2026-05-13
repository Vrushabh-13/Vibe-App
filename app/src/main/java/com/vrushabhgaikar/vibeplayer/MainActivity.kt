package com.vrushabhgaikar.vibeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.vrushabhgaikar.vibeplayer.navigation.NavGraph
import com.vrushabhgaikar.vibeplayer.presentation.components.AppBottomNavItem
import com.vrushabhgaikar.vibeplayer.presentation.components.AppMiniPlayer
import com.vrushabhgaikar.vibeplayer.presentation.components.BottomBar
import com.vrushabhgaikar.vibeplayer.presentation.player.PlayerBottomSheet
import com.vrushabhgaikar.vibeplayer.ui.theme.VibePlayerTheme
import com.vrushabhgaikar.vibeplayer.presentation.player.PlayerViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {

            VibePlayerTheme {
                val view = LocalView.current
                SideEffect {
                    val window = this@MainActivity.window

                    WindowCompat.getInsetsController(window, view)
                        .isAppearanceLightStatusBars = false   // 👈 white icons
                }
                  MainScreen()
//                OfflineMediaScreen()

            }

        }
    }
}

@Composable
fun MainScreen(
    viewModel: PlayerViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
) {
    val playerState by viewModel.playerState.collectAsState()
    val navController = rememberNavController()
    val items = listOf(
        AppBottomNavItem.Home,
        AppBottomNavItem.Songs,
        AppBottomNavItem.Video,
        AppBottomNavItem.Library
    )
    Scaffold(
        bottomBar = {
            Column {
                    if(playerState.isMiniPlayerVisible && playerState.currentMedia != null){
                        AppMiniPlayer(
                            media = playerState.currentMedia!!,
                            image = playerState.currentMedia?.thumbnailUri,
                            title = playerState.currentMedia?.title?:"",
                            artist = playerState.currentMedia?.artist?:"" ,
                            isPlaying = playerState.isPlaying,
                            onPlayerClick = {
                                viewModel.onMiniPlayerClick()
                            },
                            onPlayPauseClick = {
                                viewModel.togglePlayPause()
                            },
                            onLikeClick = {
                                playerState.currentMedia?.let { media ->

                                    val updatedMedia =
                                        homeViewModel.toggleFavorite(media)

                                    viewModel.onMediaUpdated(updatedMedia)
                                }
                            })

                    }
                BottomBar(
                    navController = navController,
                    items = items
                )
            }
        }
    ){padding ->
        Box(modifier = Modifier.padding(padding)){
            NavGraph(
                navController = navController,
                homeViewModel = homeViewModel,
                onSongClick = {media ->
                    viewModel.onMediaSelected(media)
                },

                onMediaUpdated = { updatedMedia ->
                    viewModel.onMediaUpdated(updatedMedia)
                }
            )
        }

    }
    PlayerBottomSheet(
        uiState = playerState,
        onDismiss = {
            viewModel.dismissFullPlayer()
        },
        viewModel = viewModel,
        homeViewModel = homeViewModel)

}

