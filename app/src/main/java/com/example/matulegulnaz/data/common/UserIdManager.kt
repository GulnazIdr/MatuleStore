//package com.example.matulegulnaz.data.common
//
//import com.example.matulegulnaz.data.database.local.LocalUserDao
//import com.example.matulegulnaz.data.database.local.entity.LocalUserEntity
//import io.github.jan.supabase.SupabaseClient
//import io.github.jan.supabase.auth.auth
//import java.util.UUID
//
//class UserIdManager(
//    val userDao: LocalUserDao,
//    val supabaseClient: SupabaseClient
//) {
//    protected fun getUserId(): String{
//        if(supabaseClient.auth.currentSessionOrNull() == null)
//
//    }
//
//    private suspend fun generateUserId(): String{
//        userDao.setUser(LocalUserEntity(UUID.randomUUID()))
//    }
//}