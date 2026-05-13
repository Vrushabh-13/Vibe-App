package com.vrushabhgaikar.vibeplayer.presentation.screens.library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun LibraryTopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            "Library",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)){
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = null
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )
        }
    }
}