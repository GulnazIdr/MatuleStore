package com.example.matulegulnaz.data.database.local

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class TimeConverter {
    @TypeConverter
    fun toDate(value: String): LocalDateTime{
        return LocalDateTime.parse(value)
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime): String{
        return date.toString()
    }
}