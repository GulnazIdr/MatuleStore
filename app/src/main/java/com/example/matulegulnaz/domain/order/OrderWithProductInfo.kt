package com.example.matulegulnaz.domain.order

import com.example.matulegulnaz.domain.product.SneakerInfo

data class OrderWithProductInfo(
    val order: OrderInfo,
    val product: SneakerInfo?
)
