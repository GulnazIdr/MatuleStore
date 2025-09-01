package com.example.matulegulnaz.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.matulegulnaz.R
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import com.example.matulegulnaz.ui.theme.textColorTopBar

@Composable
fun CardItem(
    onFavorite: (id: Int) -> Unit,
    onAddToCart: (id: Int) -> Unit,
    onCard: (id: Int) -> Unit,
    sneakerInfo: SneakerInfo,
    cornerImage: Int,
    modifier: Modifier = Modifier
) {

    var favoriteIcon: Int =
        if (sneakerInfo.isFavorite) R.drawable.heart
        else R.drawable.heart_empty

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp)
            .width(158.dp)
            .height(203.dp)
            .clickable(onClick = {
                onCard(sneakerInfo.id)
            })
    ) {
        Column {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                CustomRoundedButton(
                    onAction = {
                        Log.d("CLICK", "Favorite button clicked for ${sneakerInfo.id}")
                        onFavorite(sneakerInfo.id)
                    },
                    size = 28.dp,
                    iconSize = 16.dp,
                    painterResource = favoriteIcon
                )

                SubcomposeAsyncImage(
                    model = sneakerInfo.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    loading = {
                        Box{
                            CircleLoading()
                        }
                    },
                    error = {
                        Box{
                            Image(
                                painter = painterResource(R.drawable.shoe_loading),
                                contentDescription = "error shoe"
                            )
                        }
                    }
                )
            }

            Spacer(modifier = modifier.height(0.dp))

            Column {
                Text(
                    text = sneakerInfo.descr,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp,
                    color = textColorTopBar,
                    lineHeight = 16.sp
                )

                Text(
                    text = sneakerInfo.name,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = mainButtonColor,
                    lineHeight = 20.sp
                )

                Spacer(modifier = modifier.height(8.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$${sneakerInfo.price}",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = mainButtonColor,
                        lineHeight = 20.sp
                    )

                    Box(
                        modifier = modifier
                            .offset(13.dp, 12.dp)
                            .width(36.dp)
                            .height(34.dp)
                            .clip(RoundedCornerShape(16.dp, 0.dp, 16.dp, 0.dp))
                            .background(bluePrimaryColor)
                            .clickable(onClick = {onAddToCart(sneakerInfo.id)}),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painter = painterResource(id = cornerImage),
                            contentDescription = "increment icon",
                            tint = Color.White
                        )
                    }
                }

            }
        }
    }
}

//@Preview
//@Composable
//private fun CardItemPreview() {
//    CardItem(
//        {},
//        {},
//        {},
//        sneakerInfo = SneakerInfo("Best seller","Nike air max", 37.8f, R.drawable.sneaker_ex, 1, true ),
//        cornerImage = R.drawable.ic_increment
//    )
//}