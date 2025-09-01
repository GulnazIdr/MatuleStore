package com.example.matulegulnaz.presentation.cart.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor

@Composable
fun CartProductBlock(
    img: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(87.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(lightThemeSurfaceColor)
    ) {

        AsyncImage(
            model = img,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}