package com.example.matulegulnaz.base.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class BottomNavState(val navHostController: NavHostController) {

    fun <T: Any> navigateTo(route: T){
        navHostController.navigate(route){
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavState(
    navHostController: NavHostController = rememberNavController()
): BottomNavState {
    return BottomNavState(navHostController)
}