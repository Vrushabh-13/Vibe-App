package com.vrushabhgaikar.vibeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.vrushabhgaikar.vibeplayer.navigation.BottomNavItem
import com.vrushabhgaikar.vibeplayer.navigation.NavGraph
import com.vrushabhgaikar.vibeplayer.ui.theme.VibePlayerTheme
import com.vrushabhgaikar.vibeplayer.user_interface.components.BottomBar
import com.vrushabhgaikar.vibeplayer.user_interface.screens.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
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
            BottomBar(
                navController = navController,
                items = items
            )
        }
    ) {padding ->
        Box(modifier = Modifier.padding(padding)){
            NavGraph(navController = navController)
        }
    }
}