package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class RemoteOrderDto(
    val userId: Int,
    val sneakerInfo: RemoteProductDto,
    val time: LocalDateTime
)
