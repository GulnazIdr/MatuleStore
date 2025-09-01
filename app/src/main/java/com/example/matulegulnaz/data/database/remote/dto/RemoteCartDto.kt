package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCartDto (
    val userId: Int,
    val sneakerId: Int,
    val amount: Int
)