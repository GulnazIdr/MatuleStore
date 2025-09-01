package com.example.matulegulnaz.domain.search

import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.result.FetchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun insertSearch(searchInfo: SearchInfo)
    suspend fun deleteSearch(searchInfo: SearchInfo)
    fun getSearch(): Flow<List<SearchInfo>>
    suspend fun searchByKeyWord(keyWord: String): FetchResult<List<SneakerInfo>>
}