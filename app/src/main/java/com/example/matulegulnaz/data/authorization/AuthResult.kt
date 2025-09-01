package com.example.matulegulnaz.data.authorization

interface AuthResult {

    interface Mapper <T> {
        fun mapSuccess(): T
        fun mapError(connection: Boolean = false, statusCode: Int = 501) :T
    }

    fun <T> map (mapper: Mapper<T>): T

    object Success: AuthResult{
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess()
        }
    }

    class Error(val connection: Boolean = false, val statusCode: Int = 501): AuthResult{
        override fun <T> map(mapper: Mapper<T>): T{
             return mapper.mapError(connection, statusCode)
        }
    }

}