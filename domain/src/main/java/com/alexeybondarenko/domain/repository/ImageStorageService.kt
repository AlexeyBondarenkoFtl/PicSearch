package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.ImageEntity

interface ImageStorageService {
    suspend fun saveImage(imageEntity: ImageEntity)

    suspend fun getAllImages(): List<ImageEntity>

    suspend fun getImageById(id: String): ImageEntity

    suspend fun deleteImageById(id: String)

    suspend fun deleteAllImages()

    suspend fun isStorageEmpty(): Boolean
}