package com.alexeybondarenko.domain.service.imagestorage.usecase

import com.alexeybondarenko.domain.models.ImageEntity
import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService

class GetImageByIdFromStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(id: String): ImageEntity {
        return imageStorageService.getImageById(id)
    }
}