package com.example.matulegulnaz.data.database.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sneaker_data",
    foreignKeys = [ForeignKey(
        entity = LocalCategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"]
    )]
)

data class LocalSneakerEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val descr: String,
    val price: Double,
    val image: String,
    val categoryId: Int,
    val isFavorite: Boolean = false,
    val details: String,
    val amount: Int = 0
)