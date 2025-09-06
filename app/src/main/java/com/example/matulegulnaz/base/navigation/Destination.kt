package com.example.matulegulnaz.base.navigation

import com.example.matulegulnaz.base.bottom.BottomDestination
import kotlinx.serialization.Serializable

interface Destination

@Serializable
data object Splash: Destination

@Serializable
data object OnBoarding1: Destination

@Serializable
data object OnBoarding2: Destination

@Serializable
data object OnBoarding3: Destination

@Serializable
data object SignIn: Destination

@Serializable
data object SignUp: Destination

//@Serializable
//data class ForgotPassword(@Serializable val from: Destination): Destination

@Serializable
data object ForgotPassword: Destination

@Serializable
data object SearchScreen: Destination

@Serializable
data class OTPScreen(val email: String): Destination

@Serializable
data object ResetPassword: Destination

@Serializable
data object MainScreen: Destination

@Serializable
data class Catalog(val catalogId: Int): Destination

@Serializable
data class SneakerDetail(val id: Int): Destination

@Serializable
data object Profile: Destination

@Serializable
data object ProfileEdit: Destination

@Serializable
data object BarCodeScreen: Destination

@Serializable
data object CartScreen: Destination

@Serializable
data object OrderScreen: Destination

@Serializable
data object CartOrderScreen: Destination

@Serializable
data class OrderItemScreen(val orderId: Int): Destination

@Serializable
data object NotificationScreen: Destination

@Serializable
data object Favorite: BottomDestination

