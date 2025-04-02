package com.alexeybondarenko.domain.usecase.imagestorageservice

import com.alexeybondarenko.domain.repository.ImageStorageService

class DeleteImageByIdFromStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(id: String) {
        imageStorageService.deleteImageById(id)
    }
}