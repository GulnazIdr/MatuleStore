package com.example.matulegulnaz.domain.user

import com.example.matulegulnaz.data.authorization.AuthResult
import com.example.matulegulnaz.domain.result.FetchResult

interface UserRepository {
    suspend fun setCurrentUserMetaData(user: User): AuthResult
    fun getCurrentUserData(): FetchResult<User>
    suspend fun updateCurrentUserData(user: User): AuthResult
}