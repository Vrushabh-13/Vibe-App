package com.vrushabhgaikar.vibeplayer

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vrushabhgaikar.vibeplayer.navigation.NavGraph
import com.vrushabhgaikar.vibeplayer.navigation.Routes
import com.vrushabhgaikar.vibeplayer.presentation.components.AppBottomNavItem
import com.vrushabhgaikar.vibeplayer.presentation.components.AppMiniPlayer
import com.vrushabhgaikar.vibeplayer.presentation.components.BottomBar
import com.vrushabhgaikar.vibeplayer.presentation.player.PlayerBottomSheet
import com.vrushabhgaikar.vibeplayer.presentation.player.PlayerViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.ui.theme.VibePlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {

            intent?.getLongExtra(
                "songId",
                -1L
            )

            VibePlayerTheme {
                val view = LocalView.current
                SideEffect {
                    val window = this@MainActivity.window

                    WindowCompat.getInsetsController(window, view)
                        .isAppearanceLightStatusBars = false   // 👈 white icons
                }
                val playerViewModel: PlayerViewModel by viewModels()
                val homeViewModel: HomeViewModel by viewModels()
                MainScreen(playerViewModel , homeViewModel)
            }

        }
    }

    @Composable
    fun MainScreen(
        viewModel: PlayerViewModel,
        homeViewModel: HomeViewModel
    ) {
    val playerState by viewModel.playerState.collectAsState()
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


    val bottomBarRoutes = listOf(
        Routes.HOME,
        Routes.SONGS,
        Routes.VIDEO,
        Routes.LIBRARY
    )

    val showBottomBar = currentRoute in bottomBarRoutes && !playerState.isVideoFullScreen
    val items = listOf(
        AppBottomNavItem.Home,
        AppBottomNavItem.Songs,
        AppBottomNavItem.Video,
        AppBottomNavItem.Library
    )
    Scaffold(
        bottomBar = {
            Column {
                if (playerState.isMiniPlayerVisible && playerState.currentMedia != null &&
                    currentRoute != Routes.VIDEO_FULLSCREEN
                ) {
                    AppMiniPlayer(
                        media = playerState.currentMedia!!,
                        image = playerState.currentMedia?.thumbnailUri,
                        title = playerState.currentMedia?.title ?: "",
                        artist = playerState.currentMedia?.artist ?: "",
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
                if (showBottomBar) {
                    BottomBar(
                        navController = navController,
                        items = items
                    )
                }

            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavGraph(
                navController = navController,
                homeViewModel = homeViewModel,
                onSongClick = { media ->
                    viewModel.onMediaSelected(media)
                },

                onMediaUpdated = { updatedMedia ->
                    viewModel.onMediaUpdated(updatedMedia)
                },
                onCloseVideoFullScreen = {
                    viewModel.closeVideoFullScreen()
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
        homeViewModel = homeViewModel,
        navController = navController
    )

}
}

