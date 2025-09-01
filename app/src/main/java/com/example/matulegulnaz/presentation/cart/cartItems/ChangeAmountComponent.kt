package com.example.matulegulnaz.presentation.cart.cartItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun ChangeAmountComponent(
    amount: Int,
    onAddToCart: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(104.dp)
            .width(58.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bluePrimaryColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {onAddToCart()}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_increment),
                    contentDescription = "increment icon",
                    tint = Color.White
                )
            }

            Text(
                text = amount.toString(),
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color.White
            )

            IconButton(onClick = {onDecrement()}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_decrement),
                    contentDescription = "increment icon",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChangeAmountComponentPreview() {
    ChangeAmountComponent(amount = 1, {}, {})
}