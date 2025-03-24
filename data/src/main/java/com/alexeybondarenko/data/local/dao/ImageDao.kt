package com.alexeybondarenko.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexeybondarenko.data.local.model.ImageDbModel

@Dao
interface ImageDao {
    @Insert
    suspend fun insertImage(imageDbModel: ImageDbModel)

    @Query("SELECT * FROM images")
    suspend fun getImages(): List<ImageDbModel>

    @Query("SELECT * FROM images WHERE id=:id")
    suspend fun getImageById(id: String): ImageDbModel

    @Query("DELETE FROM images")
    suspend fun deleteAll()
}