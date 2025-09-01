package com.example.matulegulnaz.data.database.local

import com.example.matulegulnaz.domain.product.CategoryInfo
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.user.User

data class CategoryFetchContent (
    val categories: List<CategoryInfo>,
    val sneakers: List<SneakerInfo>
)