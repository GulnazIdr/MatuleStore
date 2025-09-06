package com.example.matulegulnaz.presentation.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.cart.common.OrderViewmodel
import com.example.matulegulnaz.presentation.cart.common.ProductOrderItem
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun OrderScreen(
    onCard: (id: Int) -> Unit,
    onBack: () -> Unit,
    orderViewmodel: OrderViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchContent = orderViewmodel.fetchOrderContentState.collectAsState().value

    CommonScaffold(snackbarHostState) {
        Column(modifier = modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                CustomTopAppBar(
                    onBack = {onBack()},
                    title = "Orders"
                )

                fetchContent.Display(
                    onSuccess = {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) { items( it.size ) { orderIndex ->

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = it[orderIndex].order.time.toString(),
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W600,
                                fontSize = 18.sp,
                                lineHeight = 22.sp,
                                color = mainButtonColor
                            )

                            ProductOrderItem(
                                orderInfo = it[orderIndex],
                                modifier = Modifier.clickable(onClick = {
                                    onCard(it[orderIndex].product!!.id)
                                })
                            )
                        }}
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
private fun OrderScreenPreview() {
    OrderScreen(
        onCard = {},
        onBack = {}
    )
}