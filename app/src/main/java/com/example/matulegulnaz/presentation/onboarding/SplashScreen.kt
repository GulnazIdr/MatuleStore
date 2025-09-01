package com.example.matulegulnaz.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.ui.theme.backgroundGradientColor
import com.example.matulegulnaz.ui.theme.ralewayFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    modifier: Modifier = Modifier,
    onDelayFinished: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(1000)
        onDelayFinished()
    }

    Box(
        modifier = modifier.fillMaxSize().background(backgroundGradientColor),
        contentAlignment = Alignment.Center
    ){
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Bold
            )) {
                append("MATULE")
            }

            withStyle(style = SpanStyle(
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Light,
                fontSize = 36.sp,
                baselineShift = BaselineShift(0.4f)
            )) {
                append(" ME")
            }
        }

        Text(
            text = text,
            color = Color.White,
            fontSize = 65.sp
        )

    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onDelayFinished = {})
}