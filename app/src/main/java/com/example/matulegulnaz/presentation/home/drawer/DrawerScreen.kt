package com.example.matulegulnaz.presentation.home.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.presentation.notification.NavItem
import com.example.matulegulnaz.presentation.notification.NotificationViewmodel
import com.example.matulegulnaz.ui.theme.drawerBackground
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun DrawerScreen(
    navigateToProfile: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToFavorite: () -> Unit,
    navigateToOrders: () -> Unit,
    navigateToNotif: () -> Unit,
    navigateToSettings: () -> Unit,
    drawerViewmodel: DrawerViewmodel = hiltViewModel(),
    notificationViewmodel: NotificationViewmodel = hiltViewModel(),
    signOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val fetchUserState = drawerViewmodel.fetchUserDataState.collectAsState().value
    val fetchNotifStatus = notificationViewmodel.fetchNotifStatus.collectAsState().value

    CommonScaffold(
        snackbarHostState = snackBarHostState
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(drawerBackground)
                .padding(padding)
        ) {
            Column {
                Column(
                    modifier = modifier.padding(start = 36.dp)
                ) {
                    fetchUserState.Display(
                        onSuccess = {
                            AsyncImage(
                                model = it.img,
                                contentDescription = "user image",
                                modifier = Modifier.size(96.dp)
                            )

                            Text(
                                text = it.name,
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = modifier.padding(top = 15.dp)
                            )
                        },
                        onError = {
                            // TODO: make it normal
                            Image(
                                painter = painterResource(R.drawable.user),
                                contentDescription = "user profile",
                                modifier = Modifier.size(96.dp)
                            )

                            Text(
                                text = "Error",
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = modifier.padding(top = 15.dp)
                            )
                        },
                        onLoading = {
                            CircleLoading()
                        },
                        onChangeButtonState = {},
                        snackBarHostState
                    )
                }

                Spacer(modifier = modifier.height(25.dp))

                NavItem(R.drawable.account, "Profile", navigateToProfile)
                NavItem(R.drawable.cart, "My cart", navigateToCart)
                NavItem(R.drawable.heart_empty, "Favorite", navigateToFavorite)
                NavItem(R.drawable.tank, "Orders", navigateToOrders)

                fetchNotifStatus.Display(
                    onSuccess = {
                        NavItem(
                            R.drawable.notification,
                            "Notfications",
                            {navigateToNotif()},
                            isNotificationRead = it.isEmpty(),
                            unReadAmount = it.size
                        )
                    },
                    onError = {},
                    onLoading = { CircleLoading() },
                    onChangeButtonState = {},
                    snackbarHostState = snackBarHostState
                )

                NavItem(R.drawable.settings, "Settings", navigateToSettings)

                Spacer(modifier = modifier.height(38.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.White,
                    modifier = modifier.padding(start = 28.dp)
                )

                NavItem(R.drawable.signout, "Sign out", signOut)
            }
        }
    }
}


@Preview
@Composable
private fun DrawerScreenPreview() {
    //DrawerScreen("Emmanuel Oyiboke", {}, {}, {}, {}, {}, {}, {})
}