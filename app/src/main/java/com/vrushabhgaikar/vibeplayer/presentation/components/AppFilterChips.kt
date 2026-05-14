package com.vrushabhgaikar.vibeplayer.presentation.components

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.domain.model.SourceType
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurpleGradient
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun AppFilterChips(selectedFilter: String ,
                   onSelect: (String) -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ChipItem(text =SourceType.ALL.value,selected = selectedFilter ,onClick  = onSelect)
        ChipItem(text = SourceType.ONLINE.value,selected = selectedFilter , onClick  = onSelect)
        ChipItem(text = SourceType.OFFLINE.value,selected = selectedFilter , onClick  = onSelect)

    }
}

@Composable
fun ChipItem(
    text: String,
    selected: String,
    onClick: (String) -> Unit
){
    val isSelected = text == selected

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(
                if (isSelected) {
                    PurpleGradient
                } else {
                    SolidColor(CardBg)
                }
            )
            .border(
                width = 1.dp,
                color = if (!isSelected)LightGray else Color.Transparent,
                shape = RoundedCornerShape(50)
            )
            .clickable{onClick(text)}
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        AppText(
            text = text,
            color = White,
            fontSize = 14.sp
        )
    }
}