package com.example.matulegulnaz.presentation.home.main

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.base.bottom.BottomNavigationGraph
import com.example.matulegulnaz.base.bottom.rememberNavState
import com.example.matulegulnaz.presentation.components.CustomRoundedButton
import com.example.matulegulnaz.presentation.home.drawer.DrawerScreen
import com.example.matulegulnaz.presentation.home.drawer.DrawerViewmodel
import com.example.matulegulnaz.presentation.home.main.component.BottomBar
import com.example.matulegulnaz.ui.theme.bluePrimaryColor

@Composable
fun MainScreen(
    onCategoryOpen: (id: Int) -> Unit,
    onCartOpen: () -> Unit,
    onSearch: () -> Unit,
    onCard: (id: Int) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateNotif: () -> Unit,
    navigateAccount: (id: Int) -> Unit,
    navigateToCart: () -> Unit,
    navigateToOrders: () -> Unit,
    navigateToSettings: () -> Unit,
    drawerViewmodel: DrawerViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavState()

    val drawerState = drawerViewmodel.drawerState.collectAsState().value
    val transition = updateTransition(targetState = drawerState)

    val scale by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500, easing = FastOutSlowInEasing)
        }
    ) {
        it.getScaleValue()
    }

    val rotate by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500, easing = FastOutSlowInEasing)
        }
    ) {
        it.getRotateValue()
    }

    val offset by transition.animateOffset(
        transitionSpec = {
            tween(durationMillis = 500, easing = FastOutSlowInEasing)
        }
    ) {
        it.getOffsetValue()
    }

    val cornerRadius by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 500, easing = FastOutSlowInEasing)
        }
    ) {
        it.getCornerRadiusValue()
    }

    Box {
        DrawerScreen(
            navigateToProfile = {navigateAccount(0)}, // TODO: set user id 
            navigateToCart = {navigateToCart()},
            navigateToFavorite = {navigateToFavorites()},
            navigateToOrders = {navigateToOrders()},
            navigateToNotif = {navigateNotif()},
            navigateToSettings = {navigateToSettings()},
            signOut = {}
        )

        Box(
            modifier = Modifier
                .offset(offset.x.dp, offset.y.dp)
                .scale(scale)
                .rotate(rotate)
                .clip(RoundedCornerShape(cornerRadius))
        ) {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackBarHostState
                    )
                },
                bottomBar = {
                    BottomBar(
                        navigateNotif = { navigateNotif() },
                        navController = navController,
                        navigateAccount = { navigateAccount(0) } // TODO: set user id
                    )
                },
                floatingActionButton = {
                    CustomRoundedButton(
                        onAction = { onCartOpen() },
                        size = 56.dp,
                        painterResource = R.drawable.cart_black,
                        backgroundColor = bluePrimaryColor,
                        modifier = modifier.offset(0.dp, (30).dp),
                        isMainCart = true
                    )
                },
                floatingActionButtonPosition = FabPosition.Center
            ) { padding ->
                BottomNavigationGraph(
                    onCategoryOpen = { onCategoryOpen(it) },
                    onSearch = { onSearch() },
                    onCard = { onCard(it) },
                    onCart = { onCartOpen() },
                    modifier = Modifier.padding(padding),
                    onDrawer = { drawerViewmodel.changeDrawerState() },
                    navController = navController
                )
            }
        }
    }
}
