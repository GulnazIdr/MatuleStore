package com.example.matulegulnaz.presentation.authorization.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import kotlinx.coroutines.delay

@Composable
fun ModalWindow(
    email: String,
    navigateToOtp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val onDismiss = LaunchedEffect(Unit) {
        delay(3000L)
    }
    navigateToOtp(email)

    Dialog(onDismissRequest = {onDismiss}) {
        Box(
            modifier = modifier
                .height(196.dp)
                .width(335.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(bluePrimaryColor),
                    contentAlignment = Alignment.Center,
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "email icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Check your email",
                    fontFamily = ralewayFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    lineHeight = 20.sp,
                    color = mainButtonColor
                )

                Text(
                    text = "We have send password recovery code in your email",
                    fontFamily = poppinsFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 20.sp,
                    color = lightGrey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ModalWindowPreview() {
    ModalWindow(email = "", navigateToOtp = {})
}