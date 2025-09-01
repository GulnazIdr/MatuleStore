package com.example.matulegulnaz.presentation.cart.cartOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.darkGrey
import com.example.matulegulnaz.ui.theme.darkerGrey
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.profileGrey

@Composable
fun ContactInfoCartComponent(
    id: Int,
    userInfo: String,
    descr: String,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = lightThemeSurfaceColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconBoxComponent(
                id = id
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = userInfo,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = mainButtonColor
                )

                Text(
                    text = descr,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = darkGrey
                )
            }
        }


        IconButton(
            onClick = { onEdit() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = ""
            )
        }

    }
}

@Composable
fun IconBoxComponent(
    id: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = profileGrey)
    ) {
        Icon(
            painter = painterResource(id),
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ContactInfoCartComponentPreview() {
    ContactInfoCartComponent(
        id = R.drawable.ic_mail,
        userInfo = "my emial",
        descr = "Email",
        onEdit = {}
    )
}