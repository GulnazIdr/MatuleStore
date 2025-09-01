package com.example.matulegulnaz.data.product

import com.example.matulegulnaz.data.common.BaseRepository
import com.example.matulegulnaz.data.database.local.CartFetchContent
import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.product.SneakerRepository
import com.example.matulegulnaz.domain.result.FetchResult
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject

class SneakerRepositoryImpl @Inject constructor(
    localSneakerRepository: SneakerRepository,
    remoteSneakerRepository: SneakerRepository,
    supabaseClient: SupabaseClient
): SneakerRepository, BaseRepository<SneakerRepository>(
    localSneakerRepository,
    remoteSneakerRepository,
    supabaseClient
) {
    override suspend fun fetchContent(): FetchResult<CategoryFetchContent> {
        return  getRepository().fetchContent()
    }

    override suspend fun addToCart(sneakerId: Int): FetchResult<Int> {
        return getRepository().addToCart(sneakerId)
    }

    override suspend fun deleteFromCart(sneakerId: Int): FetchResult<Int> {
        return getRepository().deleteFromCart(sneakerId)
    }

    override suspend fun changeAmount(
        sneakerId: Int,
        amount: Int
    ): FetchResult<Int> {
        return getRepository().changeAmount(sneakerId,amount)
    }

    override suspend fun changeFavoriteState(
        sneakerId: Int
    ): FetchResult<Int> {
        return  getRepository().changeFavoriteState(sneakerId)
    }

    override suspend fun fetchOrderContent(): FetchResult<List<OrderInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToOrderList(): FetchResult<Int> {
        TODO("Not yet implemented")
    }
}