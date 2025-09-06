package com.example.matulegulnaz.domain.product

data class SneakerInfo(
    val id: Int,
    val descr: String,
    val name: String,
    val price: Double,
    val image: String,
    val category: Int,
    val isFavorite: Boolean = false,
    val details: String = "",
    val amount: Int = 0
)