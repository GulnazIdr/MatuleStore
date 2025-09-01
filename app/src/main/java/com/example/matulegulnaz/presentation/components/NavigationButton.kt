package com.example.matulegulnaz.presentation.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun NavigationButton(
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = bluePrimaryColor,
        contentColor = Color.White
    ),
    icon: Int? = null,
    iconSize: Dp = 24.dp,
    onAction: () -> Unit,
    height: Dp = 50.dp,
    isCategory: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    var focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused = interactionSource.collectIsFocusedAsState().value

    var onClickCustom: () -> Unit = {}
    var modifierCustom = modifier.height(height)
    var colorsCustom =
        if(isCategory)
            ButtonDefaults.buttonColors().copy(
                contentColor = if(isFocused) Color.White else mainButtonColor,
                containerColor = if(isFocused) bluePrimaryColor else Color.White
            )
        else
            buttonColors

    if(isCategory) {
        modifierCustom = modifierCustom
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
        onClickCustom = {
            onAction()
            focusRequester.requestFocus()
        }
    }else{
        onClickCustom = {
            onAction()
        }
    }

    Button(
        colors = colorsCustom,
        onClick = onClickCustom,
        shape = RoundedCornerShape(13.dp),
        modifier = modifierCustom,
        enabled = enabled
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "button icon",
                    modifier = Modifier.size(iconSize)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = ralewayFamily,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = ralewayFamily,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Preview
@Composable
private fun NavigationButtonPreview() {
    NavigationButton("Get started", onAction = {})
}