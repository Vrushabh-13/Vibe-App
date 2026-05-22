package com.vrushabhgaikar.vibeplayer.presentation.components

import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.navigation.Routes

sealed class AppBottomNavItem(val route: String, val title: Int, val icon: Int) {
    object Home : AppBottomNavItem(
        route = Routes.HOME,
        title = R.string.home,
        icon = R.drawable.ic_home

    )

    object Songs : AppBottomNavItem(
        route = Routes.SONGS,
        title = R.string.songs,
        icon = R.drawable.ic_songs
    )

    object Video : AppBottomNavItem(
        route = Routes.VIDEO,
        title = R.string.video,
        icon = R.drawable.ic_video
    )

    object Library : AppBottomNavItem(
        route = Routes.LIBRARY,
        title = R.string.library,
        icon = R.drawable.ic_library
    )

}