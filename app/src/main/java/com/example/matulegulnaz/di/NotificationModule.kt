package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.notification.RemoteNotifRepositoryImpl
import com.example.matulegulnaz.domain.notification.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    @Singleton
    @Provides
    fun provideNotifRepo(supabaseClient: SupabaseClient): NotificationRepository{
        return RemoteNotifRepositoryImpl(supabaseClient)
    }

}