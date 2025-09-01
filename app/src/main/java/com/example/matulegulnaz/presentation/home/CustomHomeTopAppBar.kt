package com.example.matulegulnaz.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.components.CustomRoundedButton
import com.example.matulegulnaz.ui.theme.ralewayFamily
import com.example.matulegulnaz.ui.theme.textColorTopBar

@Composable
fun CustomHomeTopBar(
    topBarTitle: String? = null,
    titleSize: TextUnit = 16.sp,
    onLeftActionButton: () -> Unit,
    onUserActionClick: () -> Unit = {},
    isMenuScreen: Boolean = false,
    actionButtonIcon: Int? = null,
    actionButtonText: String? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(isMenuScreen) {
            IconButton(onClick = onLeftActionButton) {
                Icon(
                    painter = painterResource(id = R.drawable.hamburger),
                    contentDescription = "menu button",
                    modifier = Modifier
                        .width(26.dp)
                        .height(18.dp)
                )
            }
            topBarTitle?.let {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.onboarding1_highlight1),
                        contentDescription = "highlight on mainscreen",
                        tint = Color.Black,
                        modifier = Modifier
                            .offset(2.dp, (-4).dp)
                            .width(19.dp)
                            .height(18.dp)
                    )

                    TitleText(it, titleSize)
                }
            }
        }
        else {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = "image inside of the button",
                modifier = Modifier
                    .size(44.dp)
                    .clickable(onClick = { onLeftActionButton() }),
                tint = Color.Unspecified
            )

            topBarTitle?.let {
                TitleText(it, titleSize)
            }
        }

        actionButtonIcon?.let {
            OnCartOpen(onUserActionClick, actionButtonIcon)
        }

        actionButtonText?.let {
            TextButton(onClick = { onUserActionClick() }) {
                Text(
                    text = it,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.W700,
                    fontSize = 15.sp,
                    lineHeight = 16.sp,
                    color = textColorTopBar
                )
            }
        }
    }
}

@Composable
private fun TitleText(it: String, titleSize: TextUnit = 16.sp,) {
    Text(
        text = it,
        fontFamily = ralewayFamily,
        fontWeight = FontWeight.W600,
        lineHeight = 20.sp,
        fontSize = titleSize,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun OnCartOpen(onCartOpen: () -> Unit, image: Int){
    CustomRoundedButton(
        onAction = onCartOpen,
        size = 44.dp,
        painterResource = image,
        backgroundColor = Color.White
    )
}


@Preview
@Composable
private fun CustomTopBardPreview() {
    CustomHomeTopBar(
        topBarTitle = "Explore",
        titleSize= 16.sp,
        onLeftActionButton = {},
        onUserActionClick ={},
        actionButtonIcon = null,
        actionButtonText = "someting"
    )
}
