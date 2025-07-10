package com.alexeybondarenko.domain.service.imagestorage.usecase

import com.alexeybondarenko.domain.models.ImageEntity
import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService

class GetAllImagesFromStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(): List<ImageEntity> {
        return imageStorageService.getAllImages()
    }
}