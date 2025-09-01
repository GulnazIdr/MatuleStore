package com.example.matulegulnaz.domain.result

interface FetchResult<T> {

    fun <E> map(mapper: Mapper<E, T>): E

    interface Mapper <E,T> {
        fun mapSuccess(data: T) : E
        fun mapError(data: T?): E
    }

    class Success<T>(val data: T): FetchResult<T>{
        override fun <E> map(mapper: Mapper<E,T>): E {
            return mapper.mapSuccess(data)
        }
    }

    class Error<T>(val data: T?): FetchResult<T>{
        override fun <E> map(mapper: Mapper<E,T>): E {
            return mapper.mapError(data)
        }
    }

}