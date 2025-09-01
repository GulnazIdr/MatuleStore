package com.example.matulegulnaz.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.R

@Composable
fun CustomRoundedButton(
    onAction: () -> Unit,
    size: Dp = 52.dp,
    iconSize: Dp = 24.dp,
    painterResource: Int,
    backgroundColor: Color? = null,
    isMainCart: Boolean = false,
    modifier: Modifier = Modifier
) {
    val modifierCustom =
        if(backgroundColor != null) Modifier.clip(CircleShape).background(backgroundColor)
        else Modifier.clip(CircleShape)

    val tint = if (isMainCart) Color.White else Color.Unspecified

    Box(
        modifier = modifier
    ){
        Box(
            modifier = modifierCustom
                .size(size)
                .clickable(onClick = {
                    onAction()
                }),
            contentAlignment = Alignment.Center,
            content = {
                Icon(
                    painter = painterResource(painterResource),
                    contentDescription = "image inside of the button",
                    modifier = Modifier.size(iconSize),
                    tint = tint
                )
            }
        )
    }
}

@Preview
@Composable
private fun CustomRoundedButtonPreview() {
    CustomRoundedButton(
       onAction =  {},
       size = 48.dp,
       painterResource = R.drawable.ic_arrow_back,

    )
}