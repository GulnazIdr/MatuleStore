package com.example.matulegulnaz.data.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "search_table")
data class LocalSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val time: LocalDateTime
)