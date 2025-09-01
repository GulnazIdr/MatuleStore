package com.example.matulegulnaz.data.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_data")
data class LocalCategoryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String
)