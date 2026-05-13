package com.vrushabhgaikar.vibeplayer.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vrushabhgaikar.vibeplayer.R

@Composable
fun AppImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Dp = 0.dp,
    placeholder: Painter? = painterResource(R.drawable.img_music_thumb),
    error: Painter? = painterResource(R.drawable.img_music_thumb),
    fallback: Painter? = painterResource(R.drawable.img_music_thumb)
) {

    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = placeholder,
        error = error,
        fallback = fallback,
        modifier = modifier.clip(
            RoundedCornerShape(cornerRadius)
        )
    )
}

@Composable
fun AppImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}