package com.example.matulegulnaz.domain.order

import com.example.matulegulnaz.domain.product.SneakerInfo

data class OrdersWithProducts(
    val order: List<OrderInfo>,
    val product: List<SneakerInfo?>
)
