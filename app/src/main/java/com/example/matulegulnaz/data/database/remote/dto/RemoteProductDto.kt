package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemoteProductDto (
    val id: Int,
    val title: String,
    val descr: String,
    val price: Double,
    val image: String,
    val categoryId: Int,
    val details: String
)