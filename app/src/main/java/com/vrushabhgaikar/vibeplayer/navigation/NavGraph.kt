package com.vrushabhgaikar.vibeplayer.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vrushabhgaikar.vibeplayer.user_interface.components.BottomBar
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.HomeScreen
import com.vrushabhgaikar.vibeplayer.user_interface.screens.library.LibraryScreen
import com.vrushabhgaikar.vibeplayer.user_interface.screens.songs.SongsScreen
import com.vrushabhgaikar.vibeplayer.user_interface.screens.video.VideoScreen
import okhttp3.Route
import java.lang.reflect.Modifier

@Composable
fun NavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ){
        composable(Routes.HOME){
            HomeScreen()
        }

        composable(Routes.SONGS){
            SongsScreen()
        }

        composable(Routes.VIDEO){
            VideoScreen()
        }

        composable(Routes.LIBRARY){
            LibraryScreen()
        }
    }
}