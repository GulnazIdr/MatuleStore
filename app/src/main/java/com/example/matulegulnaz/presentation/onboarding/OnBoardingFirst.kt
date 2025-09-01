package com.example.matulegulnaz.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.ui.theme.backgroundGradientColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.mainTextColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun OnBoardingFirst(
    onStartClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackClick() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundGradientColor)
            .pointerInput(Unit) {
                detectHorizontalDragGestures{ change, dragAmount ->
                    if(dragAmount < -10f) onStartClick()
                    change.consume()
                }
            }
    ){

        Box(
            modifier = modifier
                .padding(top = 121.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboarding1_highlight1),
                contentDescription = "highlight onboarding1",
                modifier = modifier
                    .width(27.dp)
                    .height(30.dp)
                    .offset(x = (-11).dp, y = (-15).dp),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "WELCOME TO NIKE",
                    color = mainTextColor,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier.width(219.dp)
                )

                Spacer(modifier = modifier.height(19.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding1_highlight2),
                    contentDescription = "highlight2 onboarding1",
                    modifier = modifier.width(130.dp).height(15.dp)
                )
            }
        }


        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box{
                Image(
                    painter = painterResource(id = R.drawable.smile),
                    contentDescription = "smile onboarding1",
                    modifier = modifier
                        .alpha(0.3f)
                        .padding(start = 45.dp, top = 85.dp)
                )
                Image(
                    modifier = modifier.fillMaxWidth().scale(1.7f),
                    painter = painterResource(id = R.drawable.sneaker1),
                    contentDescription = "sneaker onboarding1",
                    contentScale = ContentScale.FillWidth
                )
            }

            Image(
                painter = painterResource(id = R.drawable.onboarding_progress_1),
                contentDescription = "highlight progress onboarding1"
            )
        }

        Column(
            modifier = modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = modifier.padding(start = 20.dp, top = 70.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.onboarding_highlight_3),
                        contentDescription = "highlight3 onboarding1",
                        modifier = modifier
                            .alpha(0.3f)
                            .rotate(-113f)
                    )
                }

                Box(modifier = modifier.padding(bottom = 85.dp, end = 20.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.onboarding_highlight_3),
                        contentDescription = "highlight3 onboarding1",
                        modifier = modifier.alpha(0.3f)
                    )
                }
            }

            NavigationButton(
                "Get Started",
                buttonColors = ButtonDefaults.buttonColors().copy(
                    containerColor = mainTextColor,
                    contentColor = mainButtonColor
                ),
                onAction = {onStartClick()},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 36.dp, top = 10.dp)
          )

        }

    }
}

@Preview
@Composable
private fun OnBoardingFirstPreview() {
    OnBoardingFirst(onStartClick = {}, onBackClick = {})
}

@Preview(device = "spec:width=375dp,height=668dp")
@Composable
private fun Medium() {
    OnBoardingFirst({}, {})
}

@Preview(device = "spec:width=500dp,height=1085dp")
@Composable
private fun Large() {
    OnBoardingFirst({}, {})
}

@Preview(device = "spec:width=438dp,height=990dp")
@Composable
private fun HuaweiPura() {
    OnBoardingFirst({}, {})
}