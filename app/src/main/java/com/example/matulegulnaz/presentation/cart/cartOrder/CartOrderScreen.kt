package com.example.matulegulnaz.presentation.cart.cartOrder

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.cart.common.CartResult
import com.example.matulegulnaz.presentation.cart.common.CartUserInfoComponent
import com.example.matulegulnaz.presentation.cart.common.OrderViewmodel
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.presentation.order.SuccessOrderModalWindow
import com.google.maps.android.compose.Circle

@Composable
fun CartOrderScreen(
    navigateToHome: () -> Unit,
    onBack: () -> Unit,
    productViewmodel: SneakerViewModel = hiltViewModel(),
    orderViewmodel: OrderViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchContentRes = productViewmodel.fetchResultState.collectAsState().value
    val fetchUserRes = orderViewmodel.fetchUserDataState.collectAsState().value
    val makeOrderRes = orderViewmodel.makeOrderState.collectAsState().value

    makeOrderRes.Display(
        onSuccess = {
            SuccessOrderModalWindow(
                navigateToStore = {navigateToHome()}
            )
        },
        onError = {},
        onLoading = {CircleLoading()},
        onChangeButtonState = {},
        snackbarHostState
    )

    BackHandler { onBack() }

    CommonScaffold(snackbarHostState) { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                CustomTopAppBar(
                    onBack = { onBack() },
                    title = "My cart"
                )

                Spacer(modifier = Modifier.height(62.dp))

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

                // TODO: ask deepseek how to do it professionally
                fetchContentRes.Display(
                    onSuccess = {
                        val cartProducts = it.sneakers.filter { it.amount != 0 }
                        CartResult(
                            productsPrice = cartProducts.sumOf { it.amount * it.price },
                            delivery = 10.0,
                            onOrderButton = {orderViewmodel.makeOrder()}
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

@Preview
@Composable
private fun CartOrderScreenPreview() {
    CartOrderScreen(
        onBack = {},
        navigateToHome = {}
    )
}