package com.example.matulegulnaz.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import com.example.matulegulnaz.ui.theme.superlightgrey

@Composable
fun OnBoardingSecond(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler { onBackClick() }

    Box(
        modifier
            .fillMaxSize()
            .background(backgroundGradientColor)
            .pointerInput(Unit){
                detectHorizontalDragGestures { change, dragAmount ->
                    if(dragAmount < 10f) onBackClick()
                    else if(dragAmount > 10f) onStartClick()

                    change.consume()
                }
            }
    ){
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Box{
                Image(
                    painter = painterResource(id = R.drawable.sneaker2),
                    contentDescription = "sneaker2 onboarding2",
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 25.dp),
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    painter = painterResource(id = R.drawable.onboarding_highlight_3),
                    contentDescription = "higlight onboarding2",
                    modifier = modifier
                        .align(Alignment.TopStart)
                        .alpha(0.8f)
                )
                Image(
                    painter = painterResource(id = R.drawable.smile),
                    contentDescription = "smile onboarding2",
                    modifier = modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 26.dp)
                        .alpha(0.7f)
                )
            }

            Spacer(modifier = modifier.height(48.dp))

            Column( modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Let's Start Journey With Nike",
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.W700,
                    fontSize = 34.sp,
                    color = mainTextColor,
                    lineHeight = 44.2.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = modifier.height(12.dp))

                Text(
                    text = "Smart, Gorgeous & Fashionable Collection Explore Now",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W400,
                    color = superlightgrey,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )

                Spacer(modifier = modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding_progress_2),
                    contentDescription = "onprogress2"
                )

                Spacer(modifier = modifier.height(84.dp))
            }
        }

        NavigationButton(
            text = "Next",
            buttonColors = ButtonDefaults.buttonColors().copy(
                contentColor = mainButtonColor,
                containerColor = mainTextColor
            ),
            onAction = {onStartClick()},
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 36.dp, start = 30.dp, end = 30.dp)
        )
    }
}

@Preview
@Composable
private fun OnBoardingSecondPreview() {
    OnBoardingSecond(onStartClick = {}, onBackClick = {})
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun Random() {
    OnBoardingSecond(onStartClick = {}, onBackClick = {})
}