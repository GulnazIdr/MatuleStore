package com.example.matulegulnaz.presentation.notification

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matulegulnaz.domain.notification.NotificationInfo
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import kotlinx.datetime.toKotlinLocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    notifList: List<NotificationInfo> = listOf(
        NotificationInfo("Title", "Text", java.time.LocalDateTime.now().toKotlinLocalDateTime())
    ),
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    BackHandler { onBack() }

    CommonScaffold(snackbarHostState) {
        Column(
            modifier = modifier.padding(it)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                CustomTopAppBar(
                    onBack = { onBack() },
                    title = "Notifications"
                )

                Spacer(modifier = Modifier.height(40.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(notifList.size) { item ->
                        NotificationItem(
                            notificationInfo = notifList[item]
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen(
        onBack = {}
    )
}