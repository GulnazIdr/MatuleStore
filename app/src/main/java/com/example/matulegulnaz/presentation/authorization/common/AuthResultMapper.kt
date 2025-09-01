package com.example.matulegulnaz.presentation.authorization.common

import com.example.matulegulnaz.data.authorization.AuthResult
import jakarta.inject.Inject

class AuthResultMapper @Inject constructor() : AuthResult.Mapper<AuthUiResultState> {

    override fun mapSuccess(): AuthUiResultState {
        return AuthUiResultState.Success
    }

    override fun mapError(
        connection: Boolean,
        statusCode: Int
    ): AuthUiResultState {
        val message = if(connection){
            "Network connection error"
        }else{
            when(statusCode){
                400 -> "Invalid data"
                401 -> "Such account already exists"
                404 -> "Such account doesnt exist"
                503 -> "Something went wrong. Please try again later"
                505 -> "Technical issues"
                500 -> "Unknown error"
                506 -> "You can request another code in 12 seconds"
                501 -> ""
                else -> "Something went wrong"
            }
        }

        return AuthUiResultState.Error(message)
    }


}