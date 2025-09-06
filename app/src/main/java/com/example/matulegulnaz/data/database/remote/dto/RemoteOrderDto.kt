package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteOrderDto(
    val id: Int? = null,
    val user_id: String,
    @SerialName("sneaker_id") val sneakerId: Int,
    val time: LocalDateTime
)
