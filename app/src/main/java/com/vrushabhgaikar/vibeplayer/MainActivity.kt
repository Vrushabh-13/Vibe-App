package com.vrushabhgaikar.vibeplayer

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.vrushabhgaikar.vibeplayer.navigation.BottomNavItem
import com.vrushabhgaikar.vibeplayer.navigation.NavGraph
import com.vrushabhgaikar.vibeplayer.ui.theme.VibePlayerTheme
import com.vrushabhgaikar.vibeplayer.user_interface.components.BottomBar
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.HomeScreen
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components.MiniPlayer

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
            }

        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Songs,
        BottomNavItem.Video,
        BottomNavItem.Library
    )
    Scaffold(
        bottomBar = {
            Column {
                MiniPlayer(
                    image = R.drawable.song1,
                    title = "Kesariya",
                    artist = "Arijit Singh",
                    isPlaying = true,
                    isLiked = false
                )
                BottomBar(
                    navController = navController,
                    items = items
                )
            }

        }
    ) {padding ->
        Box(modifier = Modifier.padding(padding)){
            NavGraph(navController = navController)
        }
    }
}