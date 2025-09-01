package com.example.matulegulnaz.presentation.authorization.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun RegistrTitle(
    registrTitle: String,
    registrDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 11.dp, bottom = 30.dp)
    ) {
        Text(
            text = registrTitle,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.W700,
            fontSize = 32.sp,
            color = mainButtonColor,
            textAlign = TextAlign.Center
        )

        Text(
            text = registrDescription,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            color = lightGrey,
            textAlign = TextAlign.Center,
            modifier = modifier
                .width(315.dp)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun BottomRegistrTextNav(
    question: String,
    link: String,
    onNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(bottom = 47.dp)
    ) {
        Text(
            text = question,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = lightGrey
        )

        TextButton(onClick = {onNavigateTo()}) {
            Text(
                text = link,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = mainButtonColor
            )
        }
    }
}

@Preview
@Composable
private fun RegistrTitlePreview() {
//    RegistrTitle(
//        "Hello Again!",
//        "Fill your details or continue with social media"
//    )

    BottomRegistrTextNav(
        "New user?",
        "Create account",
        {}
    )
}