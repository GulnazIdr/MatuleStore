package com.example.matulegulnaz.presentation.common

import com.example.matulegulnaz.domain.result.FetchResult
import jakarta.inject.Inject

class FetchResultMapper<T> @Inject constructor()
    : FetchResult.Mapper<FetchResultUiState<T>,T>{

    override fun mapSuccess(data: T): FetchResultUiState<T> {
        return FetchResultUiState.Success(data)
    }

    override fun mapError(data: T?): FetchResultUiState<T> {
        return FetchResultUiState.Error(data)
    }
}