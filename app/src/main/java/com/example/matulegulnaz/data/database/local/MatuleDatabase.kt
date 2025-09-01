package com.example.matulegulnaz.data.database.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.matulegulnaz.data.database.local.entity.LocalCategoryEntity
import com.example.matulegulnaz.data.database.local.entity.LocalSearchEntity
import com.example.matulegulnaz.data.database.local.entity.LocalSneakerEntity

@Database(
    entities = [
        LocalSneakerEntity::class,
        LocalCategoryEntity::class,
        LocalSearchEntity::class
    ],
    version = 1
)
@TypeConverters(TimeConverter::class)

abstract class MatuleDatabase: RoomDatabase() {

    abstract fun getLocalSneakerDao(): LocalSneakerDao

    companion object{
        @Volatile
        private var INSTANCE: MatuleDatabase ?= null

        fun getDatabase(context: Context) : MatuleDatabase{
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, MatuleDatabase::class.java, "matule.db")
                 //   .createFromAsset("databases/matule.db")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}