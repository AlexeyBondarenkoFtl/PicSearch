package com.alexeybondarenko.domain.service.photo.repository

import com.alexeybondarenko.domain.models.ImageEntity


interface PhotosService {
    suspend fun getPhotosByQuery(query: String, page: Int): List<ImageEntity>?

    suspend fun getPhotoById(id: String): ImageEntity?
}