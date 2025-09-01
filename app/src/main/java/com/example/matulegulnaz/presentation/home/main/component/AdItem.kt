package com.example.matulegulnaz.presentation.home.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.PurpleAd
import com.example.matulegulnaz.ui.theme.futuraFamiliy
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun AdItem(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(40.dp))
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(lightThemeSurfaceColor)
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = "New arrivals",
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                color = mainButtonColor
            )

            Spacer(modifier = modifier.height(24.dp))

            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Star(
                    modifier = modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 18.dp)
                )

                Star(
                    modifier = modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 15.dp, top = 10.dp)
                        .alpha(0.3f)
                )

                Star(
                    modifier = modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 4.dp, end = 60.dp)
                        .alpha(0.5f)
                )

                Star(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp, end = 27.dp)
                        .alpha(0.3f)
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Summer sale",
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 12.sp,
                        color = mainButtonColor
                    )

                    Spacer(modifier = modifier.height(4.dp))

                    Text(
                        text = "15% OFF",
                        fontFamily = futuraFamiliy,
                        fontWeight = FontWeight.W900,
                        fontSize = 36.sp,
                        lineHeight = 37.sp,
                        color = PurpleAd
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.text_new),
                    contentDescription = "text new",
                    modifier = modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                )

                Image(
                    painter = painterResource(R.drawable.sneaker_sale),
                    contentDescription = "sneaker ad",
                    modifier = modifier
                        .padding(end = 30.dp)
                        .width(112.dp)
                        .height(100.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun Star(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.star),
        contentDescription = "star icon",
        modifier = modifier
    )
}

@Preview
@Composable
private fun AdItemPreview() {
    AdItem()
}