package com.example.matulegulnaz.presentation.cart.cartItems

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange

@Composable
fun CartItemAction(
    amount: Int,
    onInc: () -> Unit,
    onDec: () -> Unit,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    var showOnChangeAmount by remember { mutableStateOf(false) }
    var showDelete by remember { mutableStateOf(false) }

    val gestureModifier = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragStart = {showDelete=true},
            onHorizontalDrag = { change, dragAmount ->

                val dragDelta = change.positionChange().x
                if (dragDelta > 10f) {
                    showOnChangeAmount = true
                    showDelete = false
                } else if (dragDelta < -10f) {
                    showDelete = true
                    showOnChangeAmount = false
                }

                change.consume()
            }
        )
    }

    Row(modifier = gestureModifier){

        AnimatedVisibility(visible = showOnChangeAmount) {
            ChangeAmountComponent(
                amount = amount,
                onAddToCart = {onInc()},
                onDecrement = {onDec()}
            )
        }

        content()

        AnimatedVisibility(visible = showDelete) {
            DeleteComponent(
                onDelete = {
                    onDelete()
                }
            )
        }
    }
}

//@Preview
//@Composable
//private fun CartItemActionPreview() {
//    CartItemAction(
//        sneakerInfo = SneakerInfo("Best seller","Nike air max", 37.8f, R.drawable.sneaker_ex, 1, true, amount = 1 ),
//        onAddToCart = {},
//        onDelete = {},
//        onDecrement = {},
//        content = {
//            CartItem(
//                sneakerInfo =  SneakerInfo(
//                    descr = "",
//                    name = "Nike air max 270 essential",
//                    price = 179.39f,
//                    image = R.drawable.card_info_ex2,
//                    category = 1,
//                    isFavorite = true
//                )
//            )
//        }
//    )
//}