package com.example.matulegulnaz.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun CustomTopAppBar(
    onBack: () -> Unit,
    title: String = "",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = "image inside of the button",
                modifier = Modifier
                    .size(44.dp)
                    .clickable(onClick = { onBack() })
                    .align(Alignment.TopStart),
                tint = Color.Unspecified
            )

            Text(
                text = title,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                color = mainButtonColor,
                modifier = Modifier.align(Alignment.Center)
            )
    }
}


@Preview
@Composable
private fun CustomTopBardPreview() {
    CustomTopAppBar(
        onBack = {},
        title = "TEXT"
    )
}
