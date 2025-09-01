package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.search.SearchRepositoryImpl
import com.example.matulegulnaz.domain.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModuleBind {
    @Binds
    @Singleton
    abstract fun provideSearchImpl(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}