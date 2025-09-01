package com.example.matulegulnaz.presentation.authorization.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.ForgotPasswordViewmodel
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily

@Composable
fun OtpItem(
    email: String,
    index: Int,
    focusRequester: FocusRequester,
    forgotPasswordViewmodel: ForgotPasswordViewmodel = hiltViewModel(),
    inputStateEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    var color by remember { mutableStateOf(lightThemeSurfaceColor) }

    Box(
        modifier = Modifier
            .width(46.dp)
            .border(width = 1.dp, color = color)
            .height(99.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(lightThemeSurfaceColor)
            .clickable(onClick = {focusRequester.requestFocus()})
            .focusRequester(focusRequester)
            .onFocusChanged{
                color = if(it.isFocused) Color.Red
                        else lightThemeSurfaceColor
            }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            BasicTextField(
                value = forgotPasswordViewmodel.getOtpItem(index),
                onValueChange = {forgotPasswordViewmodel.setOtpItem(email, it, index)},
                textStyle = TextStyle(
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    color = mainButtonColor
                ),
                enabled = inputStateEnabled
            )
        }
    }
    Spacer(modifier = Modifier.width(18.dp))
}