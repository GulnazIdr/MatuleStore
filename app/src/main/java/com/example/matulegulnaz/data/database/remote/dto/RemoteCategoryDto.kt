package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCategoryDto (
    val id: Int,
    val title: String
)