package com.example.matulegulnaz.presentation.cart.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.presentation.cart.cartOrder.ContactInfoCartComponent
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.darkGrey
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun CartUserInfoComponent(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = lightThemeSurfaceColor)
            .padding(16.dp)
    ) {
        Text(
            text = "Contact info",
            fontFamily = ralewayFamily,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W500,
            color = mainButtonColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactInfoCartComponent(
            id = R.drawable.ic_mail,
            userInfo = user.email,
            descr = "Email",
            onEdit = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactInfoCartComponent(
            id = R.drawable.ic_phone,
            userInfo = user.phone,
            descr = "Phone",
            onEdit = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Address",
            fontFamily = ralewayFamily,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W500,
            color = mainButtonColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = user.address,
            fontFamily = poppinsFamily,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.W500,
            color = darkGrey
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(101.dp)
                .background(color = bluePrimaryColor)

        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Payment",
            fontFamily = ralewayFamily,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W500,
            color = mainButtonColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(R.drawable.card),
            contentDescription = "",
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
        )

    }
}

//@Preview
//@Composable
//private fun CartUserInfoComponentPreview() {
//    CartUserInfoComponent()
//}