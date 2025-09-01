package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.domain.result.FetchResult
import com.example.matulegulnaz.presentation.common.FetchResultMapper
import com.example.matulegulnaz.presentation.common.FetchResultUiState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FetchSneakerModule {

    @Binds
    abstract fun provideFetchSneakerMapper(
        mappper: FetchResultMapper<CategoryFetchContent>
    ): FetchResult.Mapper<FetchResultUiState<CategoryFetchContent>, CategoryFetchContent>
}