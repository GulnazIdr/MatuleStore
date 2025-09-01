package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteFavoriteDto (
    val id: Int? = null,
    @SerialName("user_id") val user_id: String,
    @SerialName("product_id") val productId: Int
)