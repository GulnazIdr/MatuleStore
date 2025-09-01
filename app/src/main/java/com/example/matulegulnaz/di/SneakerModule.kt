package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.database.local.LocalSneakerDao
import com.example.matulegulnaz.data.product.LocalSneakerRepository
import com.example.matulegulnaz.data.product.RemoteSneakerRepository
import com.example.matulegulnaz.data.product.SneakerRepositoryImpl
import com.example.matulegulnaz.domain.product.SneakerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SneakerModule {

    @Provides
    @Singleton
    fun provideSneakerModule(localSneakerDao: LocalSneakerDao): LocalSneakerRepository{
        return LocalSneakerRepository(localSneakerDao)
    }

    @Provides
    @Singleton
    fun provideRemoteSneakerRepo(supabaseClient: SupabaseClient): RemoteSneakerRepository {
        return RemoteSneakerRepository(supabaseClient)
    }

    @Provides
    @Singleton
    fun provideSneakerImpl(
        localSneakerRepository: LocalSneakerRepository,
        remoteSneakerRepository: RemoteSneakerRepository,
        supabaseClient: SupabaseClient
    ): SneakerRepository{
        return SneakerRepositoryImpl(
            localSneakerRepository, remoteSneakerRepository, supabaseClient
        )
    }
}