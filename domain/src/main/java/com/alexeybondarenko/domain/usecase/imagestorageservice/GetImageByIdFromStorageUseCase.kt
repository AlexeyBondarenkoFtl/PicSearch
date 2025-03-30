package com.alexeybondarenko.domain.usecase.imagestorageservice

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.ImageStorageService

class GetImageByIdFromStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(id: String): ImageEntity {
        return imageStorageService.getImageById(id)
    }
}