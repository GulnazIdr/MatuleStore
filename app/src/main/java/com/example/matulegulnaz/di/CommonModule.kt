package com.example.matulegulnaz.di

import android.content.Context
import com.example.matulegulnaz.data.database.local.LocalSneakerDao
import com.example.matulegulnaz.data.database.local.MatuleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MatuleDatabase{
        return MatuleDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(matuleDatabase: MatuleDatabase): LocalSneakerDao{
        return matuleDatabase.getLocalSneakerDao()
    }

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://mhrdhgnmwhlremzorskz.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1ocmRoZ25td2hscmVtem9yc2t6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQ3MjA1MDIsImV4cCI6MjA3MDI5NjUwMn0.QGGC92wxTFPg0ferQYdTYq5yGNgRA3tV7KjKLKex2_E"
        ) {
            install(Auth)
            install(Postgrest)
            defaultSerializer = KotlinXSerializer()
        }
    }


}