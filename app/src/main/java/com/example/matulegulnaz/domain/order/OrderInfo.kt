package com.example.matulegulnaz.domain.order

import com.example.matulegulnaz.domain.product.SneakerInfo
import kotlinx.datetime.LocalDateTime

data class OrderInfo(
    val userId: Int,
    val sneakerInfo: SneakerInfo,
    val time: LocalDateTime
)
