package com.example.matulegulnaz.base.bottom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matulegulnaz.presentation.home.favorite.FavoriteScreen
import com.example.matulegulnaz.presentation.home.main.component.MainPage

@Composable
fun BottomNavigationGraph(
    onCart: () -> Unit,
    onCategoryOpen: (id: Int) -> Unit,
    onSearch: () -> Unit,
    onCard: (id: Int) -> Unit,
    onDrawer: () -> Unit,
    navController: BottomNavState,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController.navHostController,
        startDestination = MainPage
    ){
        composable<MainPage> {
            MainPage(
                onDrawerOpen = {onDrawer()},
                onCartOpen = {onCart()},
                onSearch = {onSearch() },
                onCategoryOpen = {onCategoryOpen(it)},
                onCard = {onCard(it)},
                modifier = modifier
            )
        }

        composable<Favorite> {
            FavoriteScreen(
                // TODO: popupto
                onBack = {navController.navigateTo(MainPage)},
                onCard = {onCard(it)}
            )
        }

    }
}