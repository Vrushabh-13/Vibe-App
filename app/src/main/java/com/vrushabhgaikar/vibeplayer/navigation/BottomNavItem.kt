package com.vrushabhgaikar.vibeplayer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import com.vrushabhgaikar.vibeplayer.R

import okhttp3.Route

sealed class BottomNavItem(val route:  String, val title: String, val icon: Int ){
    object Home: BottomNavItem(
        route = Routes.HOME,
        title = "Home",
        icon = R.drawable.ic_home
    )
    object Songs : BottomNavItem(
        route = Routes.SONGS,
        title = "Songs",
        icon = R.drawable.ic_songs
    )
    object Video : BottomNavItem(
        route = Routes.VIDEO,
        title = "Video",
        icon = R.drawable.ic_video
    )
    object Library : BottomNavItem(
        route = Routes.LIBRARY,
        title = "Library",
        icon = R.drawable.ic_library
    )

}