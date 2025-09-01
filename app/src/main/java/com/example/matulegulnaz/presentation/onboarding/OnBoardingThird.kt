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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import com.example.matulegulnaz.ui.theme.superlightgrey

@Composable
fun OnBoardingThird(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onBackClick: () -> Unit
) {

    BackHandler { onBackClick() }

    Box(
        modifier = modifier
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
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.smile),
                    contentDescription = "smile onboarding3",
                    modifier = modifier
                        .scale(1.8f)
                        .alpha(0.8f)
                        .padding(start = 52.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.sneaker3),
                    contentDescription = "sneaker onboarding3",
                    modifier = modifier.fillMaxWidth().padding(end = 26.dp).scale(1.35f),
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    painter = painterResource(id = R.drawable.ellipse),
                    contentDescription = "ellipse around sneaker",
                    modifier = modifier.scale(2.9f).align(Alignment.Center).padding(top = 10.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth().offset(y = (-10).dp).padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "You Have The Power To",
                    fontWeight = FontWeight.W700,
                    color = mainTextColor,
                    fontSize = 34.sp,
                    lineHeight = 44.2.sp,
                    fontFamily = ralewayFamily,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = modifier.height(12.dp))

                Text(
                    text = "There Are Many Beautiful And Attractive Plants To Your Room",
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    fontFamily = poppinsFamily,
                    lineHeight = 24.sp,
                    color = superlightgrey,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding_progress_3),
                    contentDescription = "onprogress3"
                )

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
                .padding(start = 30.dp, end = 30.dp, bottom = 36.dp)
        )
    }

}

@Preview
@Composable
private fun OnBoardingThirdPreview() {
    OnBoardingThird(onStartClick = {}, onBackClick = {})
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun Random() {
    OnBoardingThird(onStartClick = {}, onBackClick = {})
}