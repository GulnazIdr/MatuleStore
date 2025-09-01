package com.example.matulegulnaz.di

import android.content.Context
import com.example.matulegulnaz.BuildConfig
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
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            defaultSerializer = KotlinXSerializer()
        }
    }


}