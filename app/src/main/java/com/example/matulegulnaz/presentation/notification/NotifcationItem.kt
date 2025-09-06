package com.example.matulegulnaz.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.domain.notification.NotificationInfo
import com.example.matulegulnaz.ui.theme.darkGrey2
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.profileGrey

@Composable
fun NotificationItem(
    notificationInfo: NotificationInfo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(328.dp)
            .height(142.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(color = profileGrey)
            .padding(16.dp)
    ) {
        Column {
            // TODO: font family masivia
            Text(
                text = notificationInfo.title,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = mainButtonColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = notificationInfo.text,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = mainButtonColor
            )
        }

        Text(
            text = notificationInfo.time.toString(),
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            color = darkGrey2,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private fun NotificationItemPreview() {
//    NotificationItem(
//         notificationInfo = NotificationInfo(
//             "Title",
//             "Text",
//             java.time.LocalDateTime.now().toKotlinLocalDateTime(),
//             false
//    ))
//}