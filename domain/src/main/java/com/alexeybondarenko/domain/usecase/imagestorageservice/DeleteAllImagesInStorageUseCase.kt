package com.alexeybondarenko.domain.usecase.imagestorageservice

import com.alexeybondarenko.domain.repository.ImageStorageService

class DeleteAllImagesInStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute() {
        return imageStorageService.deleteAllImages()
    }
}