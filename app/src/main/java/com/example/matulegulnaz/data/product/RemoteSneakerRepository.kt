package com.example.matulegulnaz.data.product

import android.util.Log
import com.example.matulegulnaz.data.database.local.CartFetchContent
import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.data.database.remote.RemoteMapper
import com.example.matulegulnaz.data.database.remote.dto.RemoteCartDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteCategoryDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteFavoriteDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteOrderDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteProductDto
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.product.SneakerRepository
import com.example.matulegulnaz.domain.result.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteSneakerRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
): SneakerRepository, RemoteMapper() {

    override suspend fun fetchContent(): FetchResult<CategoryFetchContent> {
        // TODO: make it work for not registered users
        return try {
            val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(null)

            val favorites = supabaseClient.from("favorites")
                .select { filter { RemoteFavoriteDto::user_id eq user.id } }
                .decodeList<RemoteFavoriteDto>()
                .associateBy {it.productId}

            val amounts = supabaseClient.from("cart")
                .select{ filter { RemoteCartDto::userId eq user.id}}
                .decodeList<RemoteCartDto>()

            val categories = supabaseClient.from("category").select().decodeList<RemoteCategoryDto>()
            val sneakers = supabaseClient.from("sneaker").select().decodeList<RemoteProductDto>()

            val fetchContent = CategoryFetchContent(
                categories = categories.map { it.toCategory() },
                sneakers = sneakers.map { product ->
                    product.toSneaker(favorites.containsKey(product.id), amounts.size)
                }
            )
            FetchResult.Success(fetchContent)

        }catch (e: Exception){
            Log.e("FETCH CONTENT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun addToCart(
        sneakerId: Int
    ): FetchResult<Int> {

        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(sneakerId)

        return try {
            val toUpsert = RemoteCartDto(
                userId = user.id.toInt(),
                sneakerId = sneakerId,
                amount = 1
            )
            supabaseClient.from("cart").upsert(toUpsert)
            FetchResult.Success(sneakerId)
        }catch (e: Exception){
            Log.e("ADD TO CART", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun deleteFromCart(sneakerId: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
        return FetchResult.Error(sneakerId)

        return try {
            withContext(Dispatchers.IO) {
                supabaseClient.from("cart").delete {
                    filter {
                        RemoteCartDto::userId eq user.id.toInt()
                        RemoteCartDto::sneakerId eq sneakerId
                    }
                }
            }
            FetchResult.Success(sneakerId)
        }catch (e: Exception){
            Log.e("DELETE FROM CART", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun changeAmount(
        sneakerId: Int,
        amount: Int
    ): FetchResult<Int> {

        val user = supabaseClient.auth.currentSessionOrNull()?.user ?: run {
            return FetchResult.Error(sneakerId)
        }

        return try{
            withContext(Dispatchers.IO) {
                supabaseClient.from("cart").update(
                    {
                        RemoteCartDto::amount setTo amount
                    }
                ) {
                    filter {
                        RemoteCartDto::userId eq user.id.toInt()
                        RemoteCartDto::sneakerId eq sneakerId
                    }
                }
            }
            FetchResult.Success(sneakerId)
        }catch (e: Exception){
            Log.e("CHANGE AMOUNT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun changeFavoriteState(sneakerId: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
        return FetchResult.Error(sneakerId)

        return try{
            val favoritesDto = supabaseClient.from("favorites")
                .select{
                    filter {
                        RemoteFavoriteDto::productId eq sneakerId
                        RemoteFavoriteDto::user_id eq user.id
                    }
                }
                .decodeList<RemoteFavoriteDto>()

            Log.d("USER ID", favoritesDto.size.toString())

            if(favoritesDto.isEmpty())
                supabaseClient.from("favorites").insert(
                    RemoteFavoriteDto(user_id = user.id, productId = sneakerId)
                )
            else
                supabaseClient.from("favorites").delete {
                    filter {
                        RemoteFavoriteDto::user_id eq user.id
                        RemoteFavoriteDto::productId eq sneakerId
                    }
                }

            FetchResult.Success(sneakerId)
        }catch (e: Exception){
            Log.e("FETCH FAVORITE", "$e ${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun fetchOrderContent(): FetchResult<List<OrderInfo>> {
        return try {
            val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(null)

            val ordersId = supabaseClient.from("order")
                .select{ filter {
                    RemoteOrderDto::userId eq user.id
                }}.decodeList<OrderInfo>()

            val orders = supabaseClient.from("sneaker")
                .select{ filter {
                    RemoteProductDto::id eq ordersId
                }}.decodeList<SneakerInfo>()


//            val orders2 = ordersId.filter { or ->
//                orders.filter { sn ->
//                    or.sneakerInfo.id == sn.id
//                }
//            }
//
//
//            val orders2 =
//                for (sn in orders) {
//                    ordersId.filter { or ->
//                        sn.id == or.sneakerInfo.id
//                    }
//                }

            return FetchResult.Success(ordersId)

        }catch (e: Exception){
            Log.e("FETCH ORDER CONTENT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun addToOrderList(): FetchResult<Int> {
        return FetchResult.Error(null)
//        return try {
//            val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
//            return FetchResult.Error(null)
//
//            val newOrder = RemoteOrderDto(
//                userId = user.id,
//
//            )
//
//        }catch (e: Exception){
//            Log.e("FETCH ORDER CONTENT", "${e::class.simpleName} ${e.message}")
//            FetchResult.Error(null)
//        }
    }

}