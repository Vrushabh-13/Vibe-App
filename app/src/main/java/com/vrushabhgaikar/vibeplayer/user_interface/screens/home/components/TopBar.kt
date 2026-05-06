package com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleGradient
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun HomeTopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PurpleGradient)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text("Vibe",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Icon(painter = painterResource(id = R.drawable.ic_notification), contentDescription = null, tint = White)
            Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null, tint = White)
        }
    }
}