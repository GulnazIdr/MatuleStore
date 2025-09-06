package com.example.matulegulnaz.data.search

import android.util.Log
import com.example.matulegulnaz.data.database.local.LocalMapper
import com.example.matulegulnaz.data.database.local.LocalSneakerDao
import com.example.matulegulnaz.data.database.remote.RemoteMapper
import com.example.matulegulnaz.data.database.remote.dto.RemoteCartDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteFavoriteDto
import com.example.matulegulnaz.data.database.remote.dto.RemoteProductDto
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.result.FetchResult
import com.example.matulegulnaz.domain.search.SearchInfo
import com.example.matulegulnaz.domain.search.SearchRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.filter.TextSearchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dao: LocalSneakerDao,
    private val supabaseClient: SupabaseClient
): SearchRepository, LocalMapper() {

    override suspend fun insertSearch(searchInfo: SearchInfo) {
        val searchEntity = searchInfo.toSearchEntity()
        dao.addSearch(searchEntity)
    }

    override suspend fun deleteSearch(searchInfo: SearchInfo) {
        val searchEntity = searchInfo.toSearchEntity()
        dao.deleteSearch(searchEntity)
    }

    override fun getSearch(): Flow<List<SearchInfo>> {
        val searchInfo = dao.getSearch().map { it.map { it.toSearchInfo() } }
        return searchInfo
    }

    override suspend fun searchByKeyWord(keyWord: String): FetchResult<List<SneakerInfo>> {
        // TODO: make it work for not registered users
       return try {
           val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
           return FetchResult.Error(null)

           val favorites = supabaseClient.from("favorites")
               .select { filter { RemoteFavoriteDto::user_id eq user.id } }
               .decodeList<RemoteFavoriteDto>()
               .associateBy {it.productId}

           val amounts = supabaseClient.from("cart")
               .select{ filter { RemoteCartDto::user_id eq user.id }}
               .decodeList<RemoteCartDto>()

           val searchedDto = supabaseClient.from("sneaker")
               .select {
                   filter {
                       or {
                           textSearch(
                               column = "title",
                               query = keyWord,
                               config = "english",
                               textSearchType = TextSearchType.PLAINTO
                           )
                           textSearch(
                               column = "descr",
                               query = keyWord,
                               config = "english",
                               textSearchType = TextSearchType.PLAINTO
                           )
                           textSearch(
                               column = "details ",
                               query = keyWord,
                               config = "english",
                               textSearchType = TextSearchType.PLAINTO
                           )
                       }
                   }
               }.decodeList<RemoteProductDto>()

           val searchedInfo = searchedDto.map { it.toSneaker(favorites.containsKey(it.id),amounts.size) }
           FetchResult.Success(searchedInfo)
       }catch (e: Exception){
           Log.e("FILTER", "${e::class.simpleName}, ${e.message}")
           FetchResult.Error(null)
       }
    }
}