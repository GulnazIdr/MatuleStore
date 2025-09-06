package com.example.matulegulnaz.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.R
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun NavItem(
    icon: Int,
    text: String,
    onAction: () -> Unit,
    isNotificationRead: Boolean = true,
    unReadAmount: Int = 1,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(start = 28.dp, top = 30.dp)
            .clickable(onClick = onAction),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isNotificationRead)
            Box(
                modifier = Modifier
                    .offset(17.dp, (-7).dp)
                    .clip(RoundedCornerShape(50.dp))
                    .size(8.dp)
                    .background(Color.Red)
            ){Text(text = unReadAmount.toString())}

        Icon(
            painter = painterResource(icon),
            contentDescription = "navigation icon drawer",
            modifier = Modifier
                .height(18.dp)
                .width(18.dp),
            tint = Color.White
        )

        Spacer(modifier = modifier.width(30.dp))

        Text(
            text = text,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = Color.White,
            lineHeight = 20.sp
        )
    }
}

@Preview
@Composable
private fun NotificationIconPreview() {
    NavItem(
        R.drawable.notification, "Notfications", {},
        isNotificationRead = false,
    )
}