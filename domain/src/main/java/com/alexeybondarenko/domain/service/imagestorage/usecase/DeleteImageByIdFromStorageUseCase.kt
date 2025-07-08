package com.alexeybondarenko.domain.service.imagestorage.usecase

import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService

class DeleteImageByIdFromStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(id: String) {
        imageStorageService.deleteImageById(id)
    }
}