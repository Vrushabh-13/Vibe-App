package com.vrushabhgaikar.vibeplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.FavoritesScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.HistoryScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.LibraryScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.PlaylistDetailsScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.PlaylistSelectionScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.SongsScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.video.VideoFullScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.video.VideoScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    onSongClick: (MediaItemModel) -> Unit,
    onMediaUpdated: (MediaItemModel) -> Unit,
    onCloseVideoFullScreen: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = homeViewModel,
                onSongClick = onSongClick,
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(Routes.SONGS) {
            SongsScreen(
                viewModel = homeViewModel,
                onSongClick = onSongClick,
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(Routes.VIDEO) {
            VideoScreen(
                viewModel = homeViewModel,
                onVideoClick = { media ->
                    onSongClick(media)
                },
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(Routes.LIBRARY) {
            LibraryScreen(
                viewModel = homeViewModel,
                navController = navController,
                onOpenFavorites = {
                    navController.navigate(Routes.FAVORITES)
                },
                onOpenHistory = {
                    navController.navigate(Routes.HISTORY)
                }
            )
        }

        composable(Routes.FAVORITES) {
            FavoritesScreen(
                viewModel = homeViewModel,
                onSongClick = onSongClick,
                onBack = {
                    navController.popBackStack()
                },
                onMediaUpdated = onMediaUpdated
            )
        }
        composable(Routes.HISTORY) {
            HistoryScreen(
                viewModel = homeViewModel,
                onSongClick = onSongClick,
                onBack = {
                    navController.popBackStack()
                },
                onMediaUpdated = onMediaUpdated
            )

        }

        composable(
            route = "${Routes.PLAYLIST_DETAILS}/{playlistName}"
        ) { backStackEntry ->

            val playlistName =
                backStackEntry.arguments?.getString("playlistName") ?: ""

            PlaylistDetailsScreen(
                viewModel = homeViewModel,
                playlistName = playlistName,
                onBack = {
                    navController.popBackStack()
                },
                onSongClick = onSongClick,
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(
            route = "${Routes.PLAYLIST_EDIT}/{playlistName}"
        ) { backStackEntry ->

            val playlistName =
                backStackEntry.arguments?.getString("playlistName") ?: ""

            PlaylistSelectionScreen(
                viewModel = homeViewModel,
                playlistName = playlistName,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.VIDEO_FULLSCREEN) {
            VideoFullScreen(
                navController = navController,
                onCloseFullScreen = onCloseVideoFullScreen
            )
        }


    }
}