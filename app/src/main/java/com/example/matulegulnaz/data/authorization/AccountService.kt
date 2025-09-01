package com.example.matulegulnaz.data.authorization

import com.example.matulegulnaz.domain.user.User

interface AccountService {
    suspend fun signUp(user: User): AuthResult
    suspend fun signIn(user: User): AuthResult
    suspend fun signOut()
    suspend fun deleteAccount()
    suspend fun updatePassword(newPassword: String): AuthResult
    suspend fun sendOtp(email: String): AuthResult
    suspend fun checkOtp(email: String, token: String): AuthResult
}
