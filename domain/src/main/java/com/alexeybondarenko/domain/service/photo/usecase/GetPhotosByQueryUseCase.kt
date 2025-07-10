package com.alexeybondarenko.domain.service.photo.usecase

import com.alexeybondarenko.domain.models.ImageEntity
import com.alexeybondarenko.domain.service.photo.repository.PhotosService

class GetPhotosByQueryUseCase(
    private val photosService: PhotosService
) {
    suspend fun execute(query: String, page: Int): List<ImageEntity>? {
        return photosService.getPhotosByQuery(query, page)
    }
}