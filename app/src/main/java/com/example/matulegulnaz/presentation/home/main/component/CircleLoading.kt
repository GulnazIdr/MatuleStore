package com.example.matulegulnaz.presentation.home.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.middleGrey

@Composable
fun CircleLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .height(40.dp)
                .width(40.dp),
            color = bluePrimaryColor ,
            trackColor = middleGrey
        )
    }
}

@Preview
@Composable
private fun CircleLoadingPrev() {
    CircleLoading()
}