package com.example.matulegulnaz.presentation.cart.cartItems

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.cart.common.CartResult
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.lightThemeSurfaceColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.superDarkBlue

@Composable
fun CartScreen(
    navigateToOrder: () -> Unit,
    onBack: () -> Unit,
    productViewmodel: SneakerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val fetchContentResult = productViewmodel.fetchResultState.collectAsState().value
    val changeAmountResult = productViewmodel.changeAmountState.collectAsState().value
    val deletProductResult = productViewmodel.deleteResult.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    changeAmountResult.Display(
        onSuccess = {},
        onError = {
            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar(
                    message = "Something went wrong. Try again later",
                    withDismissAction = false,
                    duration = SnackbarDuration.Short
                )
            }
        },
        onLoading = {CircleLoading()},
        onChangeButtonState = {},
        snackbarHostState = snackbarHostState
    )

    deletProductResult.Display(
        onSuccess = {},
        onError = {
            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar(
                    message = "Something went wrong. Try again later",
                    withDismissAction = false,
                    duration = SnackbarDuration.Short
                )
            }
        },
        onLoading = {CircleLoading()},
        onChangeButtonState = {},
        snackbarHostState = snackbarHostState
    )

    BackHandler { onBack() }
    CommonScaffold(snackbarHostState) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(lightThemeSurfaceColor)
                .padding(padding)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                CustomTopAppBar(
                    onBack = { onBack() },
                    title = "Cart"
                )

                fetchContentResult.Display(
                    onSuccess = {
                        val cartProducts = it.sneakers.filter { it.amount != 0 }
                        Text(
                            text = "${cartProducts.sumOf { it.amount }} items",
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            color = superDarkBlue,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )

                        LazyColumn(modifier = Modifier.weight(4f)) {
                            items(cartProducts.size) { index ->
                                CartItemAction(
                                    amount = cartProducts[index].amount,
                                    onInc = {
                                        productViewmodel.changeAmount(
                                            index, cartProducts[index].amount.inc()
                                        )
                                    },
                                    onDec = {
                                        productViewmodel.changeAmount(
                                            index, cartProducts[index].amount.dec()
                                        )
                                    },
                                    onDelete = {
                                        productViewmodel.deleteFromCart(index)
                                    }
                                ) {
                                    CartItem(
                                        sneakerInfo = cartProducts[index]
                                    )
                                }
                            }
                        }

                        CartResult(
                            productsPrice = cartProducts.sumOf { it.amount * it.price },
                            delivery = 10.0,
                            modifier = Modifier
                                .weight(2.3f),
                            onOrderButton = { navigateToOrder() }
                        )

                    },
                    onError = {},
                    onLoading = { CircleLoading() },
                    onChangeButtonState = {},
                    snackbarHostState = snackbarHostState
                )
            }

        }
    }
}


@Preview
@Composable
private fun CartScreenPreview() {
    CartScreen(
//        sneakerInfoList = listOf( SneakerInfo(
//                descr = "",
//                name = "Nike air max 270 essential",
//                price = 179.39f,
//                image = "",
//                category = 1,
//                isFavorite = true
//        )),
        onBack = {},
        navigateToOrder = {}
    )
}