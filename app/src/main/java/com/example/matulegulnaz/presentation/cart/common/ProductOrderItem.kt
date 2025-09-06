package com.example.matulegulnaz.presentation.cart.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulegulnaz.domain.order.OrderWithProductInfo
import com.example.matulegulnaz.ui.theme.darkGrey
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.middleBlue
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun ProductOrderItem(
    orderInfo: OrderWithProductInfo?,
    isInOrderList: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .height(105.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)
    ) {
        Row{
            CartProductBlock(
                img = orderInfo?.product?.image
            )

            Spacer(modifier = Modifier.width(15.dp))

            Box(
                modifier = Modifier.fillMaxHeight()
            ) {
                Column {
                    if (isInOrderList) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "№ ${orderInfo?.product?.id}",
                                fontFamily = ralewayFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 14.sp,
                                color = middleBlue
                            )

                            Text(
                                text = orderInfo?.order?.time.toString(),
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 14.sp,
                                color = darkGrey,
                                lineHeight = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(7.dp))
                    }

                    Text(
                        text = "${orderInfo?.product?.name}",
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = mainButtonColor,
                        modifier = Modifier.width(145.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                }

                Row(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = "${orderInfo?.product?.price}",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = mainButtonColor,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "${orderInfo?.product?.price}",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = darkGrey
                    )
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private fun ProductOrderItemPreview() {
//    ProductOrderItem(
//        orderInfo = OrderWithProductInfo(
//            sneakerInfo = SneakerInfo(
//                descr = "",
//                name = "Nike air max 270 essential",
//                price = 179.39,
//                image = "",
//                category = 1,
//                isFavorite = true
//                ),
//            time = LocalDateTime.now().toKotlinLocalDateTime()
//        ),
//        isInOrderList = true
//    )
//}