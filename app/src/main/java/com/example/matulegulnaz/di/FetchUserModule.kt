package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.user.UserRepositoryImpl
import com.example.matulegulnaz.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FetchUserModule {

    @Provides
    @Singleton
    fun provideUserRepo(supabaseClient: SupabaseClient): UserRepository{
        return UserRepositoryImpl(supabaseClient)
    }
}