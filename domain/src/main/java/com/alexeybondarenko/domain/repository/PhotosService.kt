package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.ImageEntity


interface PhotosService {
    suspend fun getPhotosByQuery(query: String): List<ImageEntity>?

    suspend fun getPhotoById(id: String): ImageEntity?
}