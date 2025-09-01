package com.example.matulegulnaz.presentation.cart.cartItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.pastelRed

@Composable
fun DeleteComponent(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(104.dp)
            .width(58.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(pastelRed)
    ) {
        IconButton(
            onClick = {onDelete()},
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_trash),
                contentDescription = "increment icon",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun DeleteComponentPreview() {
    DeleteComponent({})
}