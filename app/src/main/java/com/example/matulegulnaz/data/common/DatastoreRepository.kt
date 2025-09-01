package com.example.matulegulnaz.data.common

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun setOnBoardingViewed(isViewed: Boolean)
    fun getOnBoardingViewed(): Flow<Boolean>

    suspend fun setLoggedInStatus(isLoggedIn: Boolean)
    fun getLoggedInStatus(): Flow<Boolean>
}