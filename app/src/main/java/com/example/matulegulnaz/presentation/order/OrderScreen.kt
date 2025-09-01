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
import com.example.matulegulnaz.base.navigation.OrderScreen
import com.example.matulegulnaz.presentation.cart.cartItems.CartItem
import com.example.matulegulnaz.presentation.common.SneakerViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun OrderScreen(
    onCard: () -> Unit,
    onBack: () -> Unit,
    productViewmodel: SneakerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val fetchContent = productViewmodel.fetchResultState.collectAsState().value

    CommonScaffold(snackbarHostState) {
        Column(modifier = modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                CustomTopAppBar(
                    onBack = {onBack()},
                    title = "Orders"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Time period",
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    color = mainButtonColor
                )

                fetchContent.Display(
                    onSuccess = {
                        val sneakers = it.sneakers
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) { items( sneakers.size ) { index ->
                                CartItem(
                                    sneakerInfo = sneakers[index],
                                    modifier = Modifier.clickable(onClick = {onCard()})
                                )
                            }
                        }
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