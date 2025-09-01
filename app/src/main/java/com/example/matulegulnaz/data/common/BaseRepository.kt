package com.example.matulegulnaz.data.common

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

abstract class BaseRepository<T>(
    val localSneakerRepository: T,
    val remoteSneakerRepository: T,
    val supabaseClient: SupabaseClient
) {
    protected fun getRepository(): T{
        if(supabaseClient.auth.currentSessionOrNull() == null)
            return localSneakerRepository
        else
            return remoteSneakerRepository
    }
}