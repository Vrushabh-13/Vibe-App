package com.vrushabhgaikar.vibeplayer.user_interface.screens.home.components

import androidx.compose.foundation.clickable
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
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.White
import com.vrushabhgaikar.vibeplayer.R


@Composable
fun SectionTitle(
    title: String,
    onViewAllClick: () -> Unit = {}
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = White)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable{ onViewAllClick()}
        ) {
            Text(
                text = "View all",
                fontSize = 14.sp,
                color = LightGray
            )
            Icon(painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = LightGray)

        }


    }
}
