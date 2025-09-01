package com.example.matulegulnaz.data.database.local

import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.user.User

data class CartFetchContent(
    val user: User,
    val cartProducts: List<SneakerInfo>
)
