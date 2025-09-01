package com.example.matulegulnaz.domain.notification

import kotlinx.datetime.LocalDateTime

data class NotificationInfo (
    val title: String,
    val text: String,
    val time: LocalDateTime
)