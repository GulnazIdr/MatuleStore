package com.example.matulegulnaz.data.database.local

import com.example.matulegulnaz.data.database.local.entity.LocalCategoryEntity
import com.example.matulegulnaz.data.database.local.entity.LocalSearchEntity
import com.example.matulegulnaz.data.database.local.entity.LocalSneakerEntity
import com.example.matulegulnaz.data.database.remote.dto.RemoteProductDto
import com.example.matulegulnaz.domain.product.CategoryInfo
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.search.SearchInfo

abstract class LocalMapper {
    protected fun LocalCategoryEntity.toCategory(): CategoryInfo{
        return CategoryInfo(id = id, name = title)
    }

    protected fun LocalSneakerEntity.toProduct(): SneakerInfo{
        return SneakerInfo(
            descr = descr,
            name = title,
            price = price,
            image = image,
            category = categoryId,
            isFavorite = isFavorite,
            details = details,
            amount = amount
        )
    }

    protected fun SearchInfo.toSearchEntity(): LocalSearchEntity{
        return LocalSearchEntity(text = text, time = time)
    }

    protected fun LocalSearchEntity.toSearchInfo(): SearchInfo{
        return SearchInfo(text = text, time = time)
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