package com.example.matulegulnaz.data.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.matulegulnaz.data.database.local.entity.LocalCategoryEntity
import com.example.matulegulnaz.data.database.local.entity.LocalSearchEntity
import com.example.matulegulnaz.data.database.local.entity.LocalSneakerEntity
import com.example.matulegulnaz.domain.search.SearchInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalSneakerDao {
    @Query("SELECT * FROM sneaker_data")
    suspend fun getSneakers(): List<LocalSneakerEntity>

    @Query("SELECT * FROM category_data")
    suspend fun getCategories(): List<LocalCategoryEntity>

    @Query("SELECT * FROM sneaker_data WHERE categoryId = :categoryId")
    suspend fun getSneakersByCategory(categoryId: Int): List<LocalCategoryEntity>

    @Query("SELECT * FROM sneaker_data WHERE amount >= 1")
    suspend fun getCartSneaker(): List<LocalCategoryEntity>

    //user action

    @Query("UPDATE sneaker_data SET isFavorite = NOT isFavorite WHERE id = :sneakerId")
    suspend fun changeFavoriteState(sneakerId: Int)

    @Query("UPDATE sneaker_data SET amount = :amount WHERE id = :sneakerId")
    suspend fun changeAmount(sneakerId: Int, amount: Int)

    //cart

    @Query("UPDATE sneaker_data SET amount = :amount WHERE id = :sneakerId")
    suspend fun addToCart(sneakerId: Int, amount: Int)

    @Query("DELETE FROM sneaker_data")
    suspend fun cleartCart()

    @Query("DELETE FROM sneaker_data WHERE id = :sneakerId")
    suspend fun deleteSneakersFromCart(sneakerId: Int)

    //search
    @Upsert
    suspend fun addSearch(searchEntity: LocalSearchEntity)

    @Query("SELECT * FROM search_table ")
    fun getSearch(): Flow<List<LocalSearchEntity>>

    @Delete
    suspend fun deleteSearch(searchEntity: LocalSearchEntity)
}