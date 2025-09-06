package com.example.matulegulnaz.data.product

import android.util.Log
import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.data.database.remote.RemoteMapper
import com.example.matulegulnaz.data.database.remote.dto.RemoteCartDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteCategoryDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteFavoriteDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteOrderDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteProductDto
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.order.OrderWithProductInfo
import com.example.matulegulnaz.domain.product.SneakerRepository
import com.example.matulegulnaz.domain.result.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
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
                .select{ filter { RemoteCartDto::user_id eq user.id}}
                .decodeList<RemoteCartDto>()
                .associateBy { it.sneaker_id }

            val categories = supabaseClient.from("category").select().decodeList<RemoteCategoryDto>()
            val sneakers = supabaseClient.from("sneaker").select().decodeList<RemoteProductDto>()

            val fetchContent = CategoryFetchContent(
                categories = categories.map { it.toCategory() },
                sneakers = sneakers.map { product ->
                    product.toSneaker(favorites.containsKey(product.id),
                        amounts[product.id]?.amount ?: 0
                    )
                }
            )
            FetchResult.Success(fetchContent)

        }catch (e: Exception){
            Log.e("FETCH CONTENT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun addToCart(
        sneaker_id: Int
    ): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(sneaker_id)

        return try {
            val toUpsert = RemoteCartDto(
                user_id = user.id,
                sneaker_id = sneaker_id,
                amount = 1
            )
            supabaseClient.from("cart").upsert(toUpsert)
            FetchResult.Success(sneaker_id)
        }catch (e: Exception){
            Log.e("ADD TO CART", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun deleteFromCart(sneaker_id: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
        return FetchResult.Error(sneaker_id)

        return try {
            withContext(Dispatchers.IO) {
                supabaseClient.from("cart").delete {
                    filter {
                        RemoteCartDto::user_id eq user.id
                        RemoteCartDto::sneaker_id eq sneaker_id
                    }
                }
            }
            FetchResult.Success(sneaker_id)
        }catch (e: Exception){
            Log.e("DELETE FROM CART", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun changeAmount(sneaker_id: Int, amount: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
        return FetchResult.Error(sneaker_id)

        return try{
            if(amount == 0)
                supabaseClient.from("cart").delete{
                    filter {
                        RemoteCartDto::user_id eq user.id
                        RemoteCartDto::sneaker_id eq sneaker_id
                    }
                }

            val count = supabaseClient.from("cart").select{
                filter {
                    RemoteCartDto::user_id eq user.id
                    RemoteCartDto::sneaker_id eq sneaker_id
                }
            }.decodeList<RemoteCartDto>()

            if (count.isEmpty()) {
                supabaseClient.from("cart").update(
                    { RemoteCartDto::amount setTo amount }
                ) {
                    filter {
                        RemoteCartDto::user_id eq user.id
                        RemoteCartDto::sneaker_id eq sneaker_id
                    }
                }
            }

            FetchResult.Success(sneaker_id)
        }catch (e: Exception){
            Log.e("CHANGE AMOUNT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun changeFavoriteState(sneaker_id: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
        return FetchResult.Error(sneaker_id)

        return try{
            val favoritesDto = supabaseClient.from("favorites")
                .select{
                    filter {
                        RemoteFavoriteDto::productId eq sneaker_id
                        RemoteFavoriteDto::user_id eq user.id
                    }
                }
                .decodeList<RemoteFavoriteDto>()

            if(favoritesDto.isEmpty())
                supabaseClient.from("favorites").insert(
                    RemoteFavoriteDto(user_id = user.id, productId = sneaker_id)
                )
            else
                supabaseClient.from("favorites").delete {
                    filter {
                        RemoteFavoriteDto::user_id eq user.id
                        RemoteFavoriteDto::productId eq sneaker_id
                    }
                }

            FetchResult.Success(sneaker_id)
        }catch (e: Exception){
            Log.e("FETCH FAVORITE", "$e ${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun fetchOrderContent(): FetchResult<List<OrderWithProductInfo>> {
        return try {
            val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(null)

            val orders = supabaseClient.from("order").select{
                filter { RemoteOrderDto::user_id eq user.id }

            }.decodeList<RemoteOrderDto>()

            val ordersId = orders.map { it.sneakerId }.distinct()

            val ordersWithProducts = supabaseClient.from("sneaker").select{
                filter { RemoteProductDto::id.contains(ordersId) }
            }.decodeList<RemoteProductDto>().associateBy { it.id }

            val fetched = orders.map {
                OrderWithProductInfo(
                    order = it.toOrder(),
                    product = ordersWithProducts[it.sneakerId]?.toSneaker()
                )
            }

            return FetchResult.Success(fetched)

        }catch (e: Exception){
            Log.e("FETCH ORDER CONTENT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun addToOrderList(order: OrderInfo): FetchResult<Int> {
        return try {
            val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(null)

            val existing = supabaseClient.from("order").select{
                filter {
                    RemoteOrderDto::user_id eq user.id
                    RemoteOrderDto::sneakerId eq order.sneakerId
                }
            }.decodeList<RemoteOrderDto>()

            if(existing.isEmpty()) {
                val newOrder = RemoteOrderDto(
                    user_id = user.id,
                    sneakerId = order.sneakerId,
                    time = order.time
                )

                supabaseClient.from("order").upsert(newOrder)
            }
            FetchResult.Success(order.sneakerId)
        }catch (e: Exception){
            Log.e("ADD TO ORDER LIST", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun getOrderById(id: Int): FetchResult<OrderWithProductInfo> {
        return try {
            val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
            return FetchResult.Error(null)

            val order = supabaseClient.from("order").select{
                filter {
                    RemoteOrderDto::user_id eq user.id
                    RemoteOrderDto::sneakerId eq id
                }
            }.decodeSingle<RemoteOrderDto>()

            val orderWithProduct = supabaseClient.from("sneaker").select{
                filter { RemoteProductDto::id eq id}
            }.decodeSingle<RemoteProductDto>()

            val fetched =
                OrderWithProductInfo(
                    order = order.toOrder(),
                    product = orderWithProduct.toSneaker()
                )

            return FetchResult.Success(fetched)

        }catch (e: Exception){
            Log.e("FETCH ORDER CONTENT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

}