package com.example.matulegulnaz.base.navigation

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.matulegulnaz.base.bottom.BottomDestination
import com.example.matulegulnaz.base.bottom.Favorite
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.AuthViewModel
import com.example.matulegulnaz.presentation.authorization.signIn.ForgotPasswordScreen
import com.example.matulegulnaz.presentation.authorization.signIn.OTPScreen
import com.example.matulegulnaz.presentation.authorization.signIn.ResetPasswordScreen
import com.example.matulegulnaz.presentation.authorization.signIn.SignInScreen
import com.example.matulegulnaz.presentation.authorization.signUp.SignUpScreen
import com.example.matulegulnaz.presentation.cart.cartItems.CartScreen
import com.example.matulegulnaz.presentation.cart.cartOrder.CartOrderScreen
import com.example.matulegulnaz.presentation.home.card.CardInfoScreen
import com.example.matulegulnaz.presentation.home.catalog.CatalogScreen
import com.example.matulegulnaz.presentation.home.main.MainScreen
import com.example.matulegulnaz.presentation.home.profile.BarCodeScreen
import com.example.matulegulnaz.presentation.home.profile.ProfileEditScreen
import com.example.matulegulnaz.presentation.home.profile.ProfileScreen
import com.example.matulegulnaz.presentation.notification.NotificationScreen
import com.example.matulegulnaz.presentation.onboarding.OnBoardingFirst
import com.example.matulegulnaz.presentation.onboarding.OnBoardingSecond
import com.example.matulegulnaz.presentation.onboarding.OnBoardingThird
import com.example.matulegulnaz.presentation.onboarding.SplashScreen
import com.example.matulegulnaz.presentation.order.OrderScreen
import com.example.matulegulnaz.presentation.order.ProductOrderInfoScreen
import com.example.matulegulnaz.presentation.search.SearchScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navigationViewModel: NavigationViewModel
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val navState = navigationViewModel.navigationState.collectAsState()

    fun navigateAndPop(destination: Destination, pop: Destination){
        navController.navigate(destination){
            popUpTo(pop){ inclusive = true}
        }
    }

    fun navigateAndPop(destination: BottomDestination, pop: Destination){
        navController.navigate(destination){
            popUpTo(pop){ inclusive = true}
        }
    }

    NavHost(
        navController = navController,
        startDestination = Splash
    ){
        composable<Splash> {
            SplashScreen(
                onDelayFinished = {
                    navState.value.navigate(navController)
                }
            )
        }

        composable<OnBoarding1> {
            OnBoardingFirst(
                onStartClick = {
                    navigateAndPop(OnBoarding2, OnBoarding1)
                },
                onBackClick = {
                    (context as Activity).finish()
                }
            )
        }

        composable<OnBoarding2> {
            OnBoardingSecond(
                onStartClick = {
                    navigateAndPop(OnBoarding3, OnBoarding2)
                },
                onBackClick = {
                    navigateAndPop(OnBoarding1, OnBoarding2)
                }
            )
        }

        composable<OnBoarding3> {
            OnBoardingThird(
                onStartClick = {
                    navigateAndPop(SignIn, OnBoarding3)
                    navigationViewModel.setOnBoardingViewed(true)
                },
                onBackClick = {
                    navigateAndPop(OnBoarding2, OnBoarding3)
                }
            )
        }

        composable<SignIn> {
            SignInScreen(
                onRecoveryClick = {
                    navigateAndPop(ForgotPassword, SignIn)
                },

                onSignIn = {
                    navigateAndPop(MainScreen, SignIn)
                },

                onCreateAccount = {
                    navigateAndPop(SignUp, SignIn)
                },
                authViewmodel = authViewModel
            )

        }

        composable<SignUp> {
            SignUpScreen(
                onBackClick = {
                    navigateAndPop(destination = SignIn, pop = SignUp)
                },
                onLogIn = {
                    navigateAndPop(SignIn, SignUp)
                },

                onSignUp = {
                    navigateAndPop(MainScreen, SignUp)
                }
            )
        }

        composable<ForgotPassword> {
          //  val args = it.toRoute<ForgotPassword>().from
            ForgotPasswordScreen(
                onBackClick = {},
                navigateToOtp = {navigateAndPop(OTPScreen(email = it), ForgotPassword) }
            )
        }

        composable<OTPScreen> {
            val args = it.toRoute<OTPScreen>().email
            OTPScreen(
                email = args,
                navigateToResetPassword = {navigateAndPop(ResetPassword, OTPScreen(email = args))},
                onBack = {navigateAndPop(ForgotPassword, OTPScreen(email = args))}
            )
        }

        composable<ResetPassword> {
            ResetPasswordScreen(
                navigateToMain = {navigateAndPop(MainScreen, ResetPassword)}
            )
        }

        composable<MainScreen> {
            MainScreen(
                onCartOpen = { navigateAndPop(CartScreen, MainScreen) },
                onSearch = { navigateAndPop(SearchScreen, MainScreen) },
                onCategoryOpen = { navigateAndPop(Catalog(it), MainScreen) },
                onCard = { navigateAndPop(SneakerDetail(it), MainScreen) },
                navigateNotif = {navigateAndPop(NotificationScreen, MainScreen)},
                navigateAccount = {navigateAndPop(Profile, MainScreen)},
                navigateToCart = {navigateAndPop(CartScreen, MainScreen)},
                navigateToOrders = {navigateAndPop(OrderScreen, MainScreen)},
                navigateToSettings = {},
                navigateToFavorites = { navigateAndPop(Favorite, MainScreen) }
            )
            navigationViewModel.setLoggedIn(true)
        }

        composable<Catalog> {
            val args = it.toRoute<Catalog>().catalogId
            CatalogScreen(
                categoryId = args,
                onBack = {navigateAndPop(MainScreen, Catalog(args))},
                onCard = { navigateAndPop(SneakerDetail(it), MainScreen)}
            )
        }

        composable<SneakerDetail> {
            val args = it.toRoute<SneakerDetail>().id
            CardInfoScreen(
                sneakerId = args,
                onBackPressed = {navigateAndPop(MainScreen, SneakerDetail(args))},
                navigateToCart = {navigateAndPop(CartScreen, SneakerDetail(args))},
            )
        }

        composable<SearchScreen> {
            SearchScreen(
                onBack = {
                    navigateAndPop(MainScreen, SearchScreen)
                },
                onCancel = {
                    navigateAndPop(MainScreen, SearchScreen)
                },
                onMicro = {},
                onCard = {navigateAndPop(SneakerDetail(it), SearchScreen)}
            )
        }

        composable<Profile> {
            ProfileScreen(
                onBack = {navigateAndPop(MainScreen, Profile)},
                onRecoveryClick = { navigateAndPop(ForgotPassword, Profile)},
                navigateToProfileEdit = {navigateAndPop(ProfileEdit, Profile)}
            )
        }

        composable<ProfileEdit> {
            ProfileEditScreen(
                onBack = {navigateAndPop(Profile, ProfileEdit)},
                onEditPhoto = {},
                onBardCode = {navigateAndPop(BarCodeScreen, ProfileEdit)}
            )
        }

        composable<BarCodeScreen> {
            BarCodeScreen()
        }

        composable<CartScreen> {
            CartScreen(
                navigateToOrder = {navigateAndPop(CartOrderScreen, CartScreen)},
                onBack = {navigateAndPop(MainScreen, CartScreen)}
            )
        }

        composable<CartOrderScreen> {
            CartOrderScreen(
                onBack = {navigateAndPop(CartScreen, CartOrderScreen)},
                navigateToHome = {navigateAndPop(MainScreen, CartOrderScreen)}
            )
        }

        composable<OrderScreen> {
            OrderScreen(
                onBack = {navigateAndPop(MainScreen, OrderScreen)},
                onCard = {navigateAndPop(OrderItemScreen, OrderScreen)}
            )
        }

//        composable<OrderItemScreen> {
//            ProductOrderInfoScreen(
//                onBack = {navigateAndPop(OrderScreen, OrderItemScreen)}
//            )
//        }

        composable<NotificationScreen> {
            NotificationScreen(
                onBack = {navigateAndPop(MainScreen, NotificationScreen)}
            )
        }
    }
}