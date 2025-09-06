package com.example.matulegulnaz.presentation.cart.cartItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.presentation.cart.common.CartProductBlock
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import com.example.matulegulnaz.ui.theme.superDarkBlue

@Composable
fun CartItem(
    sneakerInfo: SneakerInfo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(bottom = 14.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(Color.White)
        ){
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CartProductBlock(
                    img = sneakerInfo.image
                )

                Spacer(modifier = Modifier.width(30.dp))

                Column {
                    Text(
                        text = sneakerInfo.name,
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = superDarkBlue
                    )
                    Text(
                        text = "$${sneakerInfo.price}",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = superDarkBlue,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

            }
        }
    }
}

@Preview
@Composable
private fun CartItemPreview() {
    CartItem(
        sneakerInfo =  SneakerInfo(
            id = 0,
            descr = "",
            name = "Nike air max 270 essential",
            price = 179.39,
            image = "",
            category = 1,
            isFavorite = true
        )
    )
}