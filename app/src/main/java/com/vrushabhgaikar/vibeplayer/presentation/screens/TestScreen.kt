package com.vrushabhgaikar.vibeplayer.presentation.screens

import android.R.attr.button
import android.R.attr.text
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg

@OptIn(UnstableApi::class)
@Composable
fun TestScreen(){
    var text by rememberSaveable { mutableStateOf("") }
    var a by remember { mutableStateOf(0) }
//    Log.d("Initial value","a = ${a} and b = ${b} value not change")
    var b by remember { mutableStateOf(0) }
    var c by remember { mutableStateOf(0) }

    Log.d("Initial value","a = ${a} and b = ${b} value")
    Add(6, 8, text = "3")
    Log.d("Initial value","a = ${a} and b = ${b} value not change")
}

@OptIn(UnstableApi::class)
@Composable
fun Add(a: Int, b: Int, text: String) {
    var count by remember { mutableStateOf(0) }
    Log.d("Initial value","a = ${a} and b = ${b} value not change")


    count = a + b
    Row(
        modifier = Modifier.fillMaxSize().background(Color.Yellow),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ){
        Row() {

            Text("count is $count")
            Button(
                onClick = {count+ 1}
            ) {
                Text("Click")
            }

        }



    }
}

