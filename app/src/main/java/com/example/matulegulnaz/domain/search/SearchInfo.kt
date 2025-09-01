package com.example.matulegulnaz.domain.search

import kotlinx.datetime.LocalDateTime

data class SearchInfo(
    val id: Int = 0,
    val text: String,
    val time: LocalDateTime
)
