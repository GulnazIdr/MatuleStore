//package com.example.matulegulnaz.data.database.local
//
//import androidx.room.Dao
//import androidx.room.Query
//import androidx.room.Upsert
//import com.example.matulegulnaz.data.database.local.entity.LocalUserEntity
//import java.util.UUID
//
//@Dao
//interface LocalUserDao {
//    @Query("SELECT uid FROM user_table")
//    suspend fun getUser(): UUID?
//
//    @Upsert
//    suspend fun setUser(user: LocalUserEntity)
//}