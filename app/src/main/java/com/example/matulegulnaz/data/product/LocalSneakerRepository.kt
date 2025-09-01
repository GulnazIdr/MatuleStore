package com.example.matulegulnaz.data.product

import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.data.database.local.LocalMapper
import com.example.matulegulnaz.data.database.local.LocalSneakerDao
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.product.SneakerRepository
import com.example.matulegulnaz.domain.result.FetchResult
import javax.inject.Inject

class LocalSneakerRepository @Inject constructor(
    private val localSneakerDao: LocalSneakerDao
): SneakerRepository, LocalMapper() {

    override suspend fun fetchContent(): FetchResult<CategoryFetchContent> {
        val content = CategoryFetchContent(
            sneakers = localSneakerDao.getSneakers().map { it.toProduct() },
            categories = localSneakerDao.getCategories().map { it.toCategory() }
        )

        return FetchResult.Success(content)
    }

    override suspend fun addToCart(sneakerId: Int): FetchResult<Int> {
        localSneakerDao.addToCart(sneakerId = sneakerId, amount = 1)
        return FetchResult.Success(sneakerId)
    }

    override suspend fun deleteFromCart(sneakerId: Int): FetchResult<Int> {
        localSneakerDao.deleteSneakersFromCart(sneakerId = sneakerId)
        return FetchResult.Success(sneakerId)
    }

    override suspend fun changeAmount(sneakerId: Int, amount: Int): FetchResult<Int> {
        localSneakerDao.changeAmount(sneakerId = sneakerId, amount = amount)
        return FetchResult.Success(sneakerId)
    }

    override suspend fun changeFavoriteState(sneakerId: Int): FetchResult<Int> {
        localSneakerDao.changeFavoriteState(sneakerId = sneakerId)
        return FetchResult.Success(sneakerId)
    }

    override suspend fun fetchOrderContent(): FetchResult<List<OrderInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToOrderList(): FetchResult<Int> {
        TODO("Not yet implemented")
    }

//    override suspend fun addToOrderList(): FetchResult {
//
//        return TODO("Provide the return value")
//    }
//
//    override suspend fun deleteFromOrderList(): FetchResult {
//        return TODO("Provide the return value")
//    }

}