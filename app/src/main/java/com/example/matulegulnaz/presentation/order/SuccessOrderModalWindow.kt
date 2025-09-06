package com.example.matulegulnaz.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.ui.theme.lightBlue
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun SuccessOrderModalWindow(
    navigateToStore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {navigateToStore()}
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .height(375.dp)
                .width(335.dp)
                .background(color = lightThemeSurfaceColor)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier.clip(RoundedCornerShape(100.dp)).size(134.dp)
                        .background(color = lightBlue)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = "",
                        modifier = Modifier.size(86.dp).align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Your order was successfull",
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.W500,
                    lineHeight = 28.sp,
                    fontSize = 20.sp,
                    color = mainButtonColor,
                    modifier = Modifier.width(159.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    text = "Go back",
                    onAction = { navigateToStore() },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SuccessOrderModalWindowPreview() {
    SuccessOrderModalWindow(
        navigateToStore = {}
    )
}