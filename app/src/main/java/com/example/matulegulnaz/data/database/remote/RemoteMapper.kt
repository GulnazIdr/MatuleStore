package com.example.matulegulnaz.data.database.remote

import com.example.matulegulnaz.data.database.remote.dto.RemoteCategoryDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteProductDto
import com.example.matulegulnaz.domain.product.CategoryInfo
import com.example.matulegulnaz.domain.product.SneakerInfo

abstract class RemoteMapper {

    protected fun RemoteCategoryDto.toCategory(): CategoryInfo{
        return CategoryInfo(
            id = id,
            name = title
        )
    }

    protected fun RemoteProductDto.toSneaker(
        isFavorite: Boolean,
        amount: Int
    ): SneakerInfo{
        return SneakerInfo(
            descr = descr,
            name = title,
            price = price,
            image = image,
            category = categoryId,
            details = details,
            isFavorite = isFavorite,
            amount = amount
        )
    }
}