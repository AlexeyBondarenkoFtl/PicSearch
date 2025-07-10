package com.alexeybondarenko.domain.service.imagestorage.usecase

import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService

class DeleteAllImagesInStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute() {
        imageStorageService.deleteAllImages()
    }
}