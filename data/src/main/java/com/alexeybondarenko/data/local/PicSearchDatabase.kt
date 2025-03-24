package com.alexeybondarenko.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexeybondarenko.data.local.dao.ImageDao
import com.alexeybondarenko.data.local.model.ImageDbModel

@Database(
    entities = [
        ImageDbModel::class,
    ],
    version = 1
)
abstract class PicSearchDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: PicSearchDatabase? = null

        private const val DB_NAME = "ps_database"

        fun getInstance(context: Context): PicSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PicSearchDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}