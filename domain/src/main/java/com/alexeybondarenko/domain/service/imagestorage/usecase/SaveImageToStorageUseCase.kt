package com.alexeybondarenko.domain.service.imagestorage.usecase

import com.alexeybondarenko.domain.models.ImageEntity
import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService

class SaveImageToStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(imageEntity: ImageEntity) {
        imageStorageService.saveImage(imageEntity)
    }
}