package com.example.matulegulnaz.data.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastoreRepositoryImpl @Inject constructor(
    private val dataStorePref: DataStore<Preferences>
): DatastoreRepository {

    val BOARDING_VIEWED = booleanPreferencesKey("boardingViewState")
    val LOGGED_IN = booleanPreferencesKey("logginState")

    override suspend fun setOnBoardingViewed(isViewed: Boolean) {
        dataStorePref.edit { preferences ->
            preferences[booleanPreferencesKey(BOARDING_VIEWED.toString())] = isViewed
        }
    }

    override fun getOnBoardingViewed(): Flow<Boolean> {
        return dataStorePref.data.map { preferences ->
            preferences[BOARDING_VIEWED] ?: false
        }
    }

    override suspend fun setLoggedInStatus(isLoggedIn: Boolean) {
        dataStorePref.edit { pref ->
            pref[booleanPreferencesKey(LOGGED_IN.toString())] = isLoggedIn
        }
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStorePref.data.map { pref ->
            pref[LOGGED_IN] ?: false
        }
    }
}