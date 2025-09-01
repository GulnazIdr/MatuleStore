package com.example.matulegulnaz.data.onboarding

import com.example.matulegulnaz.base.navigation.NavigationRepository
import com.example.matulegulnaz.data.common.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NavigationRepositoryImpl @Inject constructor (
    private val dataStoreRepository: DatastoreRepository
): NavigationRepository {

    override suspend fun saveOnBoardingViewed(isViewed: Boolean) {
        dataStoreRepository.setOnBoardingViewed(isViewed)
    }

    override fun getOnBoardingViewed(): Flow<Boolean> {
        return dataStoreRepository.getOnBoardingViewed()
    }

    override suspend fun saveLoggedIn(isLoggedIn: Boolean) {
        dataStoreRepository.setLoggedInStatus(isLoggedIn)
    }

    override fun getLoggedIn(): Flow<Boolean> {
        return dataStoreRepository.getLoggedInStatus()
    }
}