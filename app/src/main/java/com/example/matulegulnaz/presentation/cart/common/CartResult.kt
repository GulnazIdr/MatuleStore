package com.example.matulegulnaz.presentation.cart.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import com.example.matulegulnaz.ui.theme.superDarkBlue

@Composable
fun CartResult(
    onOrderButton: () -> Unit,
    productsPrice: Double,
    delivery: Double,
    modifier: Modifier = Modifier
) {
    val width = LocalWindowInfo.current.containerSize.width.dp.value
    val padding = with(LocalDensity.current) {20.dp.toPx()}
    val dash = with(LocalDensity.current) {6.dp.toPx()}

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .align(Alignment.BottomEnd)
                .background(Color.White)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(34.dp))

            Cost(
                text = "Subtotal",
                price = productsPrice
            )

            Spacer(modifier = Modifier.height(10.dp))

            Cost(
                text = "Delivery",
                price = delivery
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth().drawBehind{
                    drawLine(
                        start = Offset(x = 0f, y = 7f),
                        end = Offset(x = width-padding, y = 7f),
                        color = lightGrey,
                        strokeWidth = 6f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(dash, dash)    ,
                            phase = dash
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Cost(
                text = "TotalCost",
                price = productsPrice + delivery,
                colorName = mainButtonColor,
                colorPrice = bluePrimaryColor
            )

            Spacer(modifier = Modifier.height(30.dp))

            NavigationButton(
                text = "Checkout",
                onAction = {onOrderButton()},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun Cost(
    text : String,
    price: Double,
    colorName: Color  = lightGrey,
    colorPrice: Color = superDarkBlue,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            color = colorName
        )

        Text(
            text = "$$price",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = colorPrice
        )
    }
}

@Preview
@Composable
private fun CartResultPreview() {
    CartResult(
        productsPrice = 300.0,
        delivery = 10.0,
        onOrderButton = {}
    )
}