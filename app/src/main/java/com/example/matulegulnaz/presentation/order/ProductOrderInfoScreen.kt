package com.example.matulegulnaz.presentation.order

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.presentation.cart.common.CartUserInfoComponent
import com.example.matulegulnaz.presentation.cart.common.OrderViewmodel
import com.example.matulegulnaz.presentation.cart.common.ProductOrderItem
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.darkGrey
import com.example.matulegulnaz.ui.theme.poppinsFamily

@Composable
fun ProductOrderInfoScreen(
    orderId: Int,
    onBack: () -> Unit,
    orderViewmodel: OrderViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchUserRes = orderViewmodel.fetchUserDataState.collectAsState().value
    val fetchOrderById = orderViewmodel.fetchOrderById.collectAsState().value

    LaunchedEffect(Unit) {
        orderViewmodel.getOrderById(orderId)
    }

    BackHandler { onBack() }

    CommonScaffold(snackbarHostState) { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                fetchOrderById.Display(
                    onSuccess = { orderInfo ->
                        CustomTopAppBar(
                            onBack = { onBack() },
                            title = "${orderInfo.product?.id}"
                        )

                        Text(
                            text = orderInfo.order.time.toString(),
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = darkGrey,
                            lineHeight = 20.sp,
                            modifier = Modifier.align(Alignment.End)
                        )

                        Spacer(modifier = Modifier.height(17.dp))

                        ProductOrderItem(
                            orderInfo = orderInfo,
                            isInOrderList = true
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

                Spacer(modifier = Modifier.height(12.dp))

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

                Spacer(modifier = Modifier.height(17.dp))

                Image(
                    painter = painterResource(R.drawable.bar),
                    contentDescription = "user bar",
                    modifier = Modifier
                        .width(337.dp)
                        .height(70.dp)
                        .clickable(onClick = {  })
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun ProductOrderInfoScreenPreview() {
//    ProductOrderInfoScreen(
//        onBack = {}
//    )
//}