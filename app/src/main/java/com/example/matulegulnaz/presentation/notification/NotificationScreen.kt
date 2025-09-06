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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    onBack: () -> Unit,
    notificationViewmodel: NotificationViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchContentState = notificationViewmodel.fetchContentRes.collectAsState().value
    val updateNotificationState =
        notificationViewmodel.updateNotificationState.collectAsState().value

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

                fetchContentState.Display(
                    onSuccess = {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(it.size) { item ->
                                NotificationItem(
                                    notificationInfo = it[item]
                                )
                            }
                        }
                    },
                    onError = {},
                    onLoading = { CircleLoading() },
                    onChangeButtonState = {},
                    snackbarHostState
                )
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