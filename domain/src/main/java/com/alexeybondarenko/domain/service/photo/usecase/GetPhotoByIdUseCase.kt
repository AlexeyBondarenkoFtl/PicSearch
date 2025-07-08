package com.alexeybondarenko.domain.service.photo.usecase

import com.alexeybondarenko.domain.models.ImageEntity
import com.alexeybondarenko.domain.service.photo.repository.PhotosService

class GetPhotoByIdUseCase(
    private val photosService: PhotosService
) {
    suspend fun execute(id: String): ImageEntity? {
        return photosService.getPhotoById(id)
    }
}