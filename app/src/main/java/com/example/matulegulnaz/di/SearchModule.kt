package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.database.local.LocalSneakerDao
import com.example.matulegulnaz.data.search.SearchRepositoryImpl
import com.example.matulegulnaz.domain.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    @Singleton
    fun provideSearchModule(dao: LocalSneakerDao, supabaseClient: SupabaseClient)
    : SearchRepositoryImpl{
        return SearchRepositoryImpl(dao, supabaseClient)
    }
}