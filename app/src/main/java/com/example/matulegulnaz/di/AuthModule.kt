package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.authorization.AccountService
import com.example.matulegulnaz.data.authorization.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.auth.Auth


@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    fun provideRepository(repository: AccountServiceImpl): AccountService
}