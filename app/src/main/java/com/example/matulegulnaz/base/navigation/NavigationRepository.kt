package com.example.matulegulnaz.base.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationRepository {
    suspend fun saveOnBoardingViewed(isViewed: Boolean)
    fun getOnBoardingViewed(): Flow<Boolean>

    suspend fun saveLoggedIn(isLoggedIn: Boolean)
    fun getLoggedIn(): Flow<Boolean>
}