package com.example.matulegulnaz.presentation.common

import kotlinx.datetime.LocalDateTime

class DateTImeFormatter {
    fun formatDateTime(time: LocalDateTime): String {
        val format = LocalDateTime.Format {
            year()
            monthNumber()
            day()
        }

        return format.format(time)
    }
}