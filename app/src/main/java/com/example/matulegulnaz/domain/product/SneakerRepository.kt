package com.example.matulegulnaz.domain.product

import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.order.OrderWithProductInfo
import com.example.matulegulnaz.domain.order.OrdersWithProducts
import com.example.matulegulnaz.domain.result.FetchResult

interface SneakerRepository {
    suspend fun fetchContent(): FetchResult<CategoryFetchContent>
    suspend fun addToCart(sneakerId: Int): FetchResult<Int>
    suspend fun deleteFromCart(sneakerId: Int): FetchResult<Int>
    suspend fun changeAmount(sneakerId: Int, amount: Int): FetchResult<Int>
    suspend fun changeFavoriteState(sneakerId: Int): FetchResult<Int>
    suspend fun fetchOrderContent(): FetchResult<List<OrderWithProductInfo>>
    suspend fun addToOrderList(order: OrderInfo): FetchResult<Int>
    suspend fun getOrderById(id: Int): FetchResult<OrderWithProductInfo>
}