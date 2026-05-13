package com.vrushabhgaikar.vibeplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AppGradientOverlay(
    modifier: Modifier = Modifier,
    startColor: Color = Color.Transparent,
    endColor: Color = Color.Black.copy(alpha = 0.6f)
) {

    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    startColor,
                    endColor
                )
            )
        )
    )
}