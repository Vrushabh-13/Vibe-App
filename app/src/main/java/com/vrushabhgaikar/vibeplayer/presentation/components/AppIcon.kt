package com.vrushabhgaikar.vibeplayer.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//@Composable
//fun AppIcon(
//    painter: Painter,
//    contentDescription: String? = null,
//    modifier: Modifier = Modifier,
//    tint: Color = MaterialTheme.colorScheme.onBackground,
//    size: Dp = 24.dp,
//    onClick: (() -> Unit)? = null
//) {
//
//    val clickableModifier = if (onClick != null) {
//        modifier.clickable { onClick() }
//    } else {
//        modifier
//    }
//
//    Icon(
//        painter = painter,
//        contentDescription = contentDescription,
//        tint = tint,
//        modifier = clickableModifier.size(size)
//    )
//}


@Composable
fun AppIcon(
    @DrawableRes icon: Int? = null,
    painter: Painter? = null,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    size: Dp = 24.dp,
    onClick: (() -> Unit)? = null
) {

    val clickableModifier = if (onClick != null) {
        modifier.clickable { onClick() }
    } else {
        modifier
    }

    val iconPainter = painter ?: painterResource(icon!!)

    Icon(
        painter = iconPainter,
        contentDescription = contentDescription,
        tint = tint,
        modifier = clickableModifier.size(size)
    )
}