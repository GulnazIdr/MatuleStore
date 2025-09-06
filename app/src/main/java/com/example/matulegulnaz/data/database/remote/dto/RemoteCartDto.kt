package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCartDto (
    val id: Int? = null,
    val user_id: String,
    val sneaker_id: Int,
    val amount: Int
)