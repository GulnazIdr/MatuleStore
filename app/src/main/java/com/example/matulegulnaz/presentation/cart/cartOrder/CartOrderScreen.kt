package com.example.matulegulnaz.presentation.cart.cartOrder

import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.presentation.cart.common.CartResult
import com.example.matulegulnaz.presentation.cart.common.CartUserInfoComponent
import com.example.matulegulnaz.presentation.cart.common.OrderViewmodel
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.presentation.order.SuccessOrderModalWindow
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CartOrderScreen(
    navigateToHome: () -> Unit,
    onBack: () -> Unit,
    productViewmodel: SneakerViewModel = hiltViewModel(),
    orderViewmodel: OrderViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var isModalOpen = remember { orderViewmodel.isModalOpen }
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchContentRes = productViewmodel.fetchResultState.collectAsState().value
    val fetchUserRes = orderViewmodel.fetchUserDataState.collectAsState().value
    val makeOrderRes = orderViewmodel.makeOrderState.collectAsState().value

    var customModifier =
        if(isModalOpen.value) modifier
            .fillMaxSize()
            .background(Color.White)
            .blur(radius = 4.dp)
        else modifier
            .fillMaxSize()
            .background(Color.White)

    if (isModalOpen.value) {
        SuccessOrderModalWindow(
            navigateToStore = { navigateToHome() }
        )
    }

    makeOrderRes.map {
        it.Display(
            onSuccess = {},
            onError = {},
            onLoading = {
                LaunchedEffect(Unit) {
                    snackbarHostState.showSnackbar(
                        message = "Making order..",
                        withDismissAction = false,
                        duration = SnackbarDuration.Long
                    )
                }
            },
            onChangeButtonState = {},
            snackbarHostState
        )
    }

    BackHandler { onBack() }

    CommonScaffold(snackbarHostState) { padding ->
        Column(
            modifier = customModifier.padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                CustomTopAppBar(
                    onBack = { onBack() },
                    title = "My cart"
                )

                Spacer(modifier = Modifier.height(46.dp))

                fetchUserRes.Display(
                    onSuccess = {
                        CartUserInfoComponent(user = it)
                    },
                    onError = {
                        LaunchedEffect(Unit) {
                            snackbarHostState.showSnackbar(
                                message = "Something went wrong.",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
                    onLoading = { CircleLoading() },
                    onChangeButtonState = {},
                    snackbarHostState
                )

                fetchContentRes.Display(
                    onSuccess = {
                        val cartProducts = it.sneakers.filter { it.amount != 0 }
                        val orders = cartProducts.map { product ->
                            OrderInfo(
                                sneakerId = product.id,
                                time = LocalDateTime.now().toKotlinLocalDateTime()
                            )
                        }
                        CartResult(
                            productsPrice = cartProducts.sumOf { it.amount * it.price },
                            delivery = 10.0,
                            onOrderButton = {orderViewmodel.makeOrder(orders)}
                        )
                    },
                    onError = {
                        LaunchedEffect(Unit) {
                            snackbarHostState.showSnackbar(
                                message = "Something went wrong.",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
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
private fun CartOrderScreenPreview() {
    CartOrderScreen(
        onBack = {},
        navigateToHome = {}
    )
}