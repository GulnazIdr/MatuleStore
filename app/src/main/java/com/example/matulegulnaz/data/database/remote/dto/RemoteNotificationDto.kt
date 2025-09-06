package com.example.matulegulnaz.data.database.remote.dto

import kotlinx.datetime.LocalDateTime

data class RemoteNotificationDto(
    val id: Int? = null,
    val title: String,
    val content: String,
    val time: LocalDateTime,
    val isRead: Boolean = false,
    val user_id: String
)