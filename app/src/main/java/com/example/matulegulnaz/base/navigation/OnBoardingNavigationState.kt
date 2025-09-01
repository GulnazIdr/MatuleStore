package com.example.matulegulnaz.base.navigation

import androidx.navigation.NavController

interface OnBoardingNavigationState {
    fun navigate(navController: NavController)

    object NavigateToHome : OnBoardingNavigationState{
        override fun navigate(navController: NavController) {
            navController.navigate(MainScreen){
                popUpTo<MainScreen>{inclusive = true}
            }
        }
    }

    object NavigateToSignIn: OnBoardingNavigationState{
        override fun navigate(navController: NavController) {
            navController.navigate(SignIn){
                popUpTo<SignIn>{inclusive = true}
            }
        }
    }

    object NavigateToOnBoardingFirst: OnBoardingNavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(OnBoarding1){
                popUpTo<OnBoarding1>{inclusive = true}
            }
        }
    }

}