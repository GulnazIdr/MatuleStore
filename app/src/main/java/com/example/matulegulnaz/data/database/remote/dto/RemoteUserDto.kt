package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemoteUserDto (
    val name: String = "",
    val surname: String = "",
    val address: String = "",
    val phone: String = "",
    val password: String = "",
    val email: String = "",
    val img: String = ""
)