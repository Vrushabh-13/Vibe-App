package com.vrushabhgaikar.vibeplayer.navigation

import com.vrushabhgaikar.vibeplayer.presentation.screens.FavoritesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.LibraryScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.songs.SongsScreen
import com.vrushabhgaikar.vibeplayer.presentation.screens.video.VideoScreen


@Composable
fun NavGraph(navController: NavHostController,
             homeViewModel: HomeViewModel,
             onSongClick: (MediaItemModel) -> Unit,
             onMediaUpdated: (MediaItemModel) -> Unit){

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ){
        composable(Routes.HOME){
            HomeScreen(
                viewModel = homeViewModel,
                onSongClick = onSongClick,
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(Routes.SONGS){
            SongsScreen(
                viewModel = homeViewModel,
                onSongClick = onSongClick,
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(Routes.VIDEO){
            VideoScreen(
                viewModel = homeViewModel,
                onVideoClick = { media ->
                    onSongClick(media)
                },
                onMediaUpdated = onMediaUpdated
            )
        }

        composable(Routes.LIBRARY){
            LibraryScreen(viewModel = homeViewModel,
                navController = navController,
                onOpenFavorites = {
                    navController.navigate(Routes.FAVORITES)
                })
        }

        composable(Routes.FAVORITES) {
            FavoritesScreen(
                viewModel = homeViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }


    }
}