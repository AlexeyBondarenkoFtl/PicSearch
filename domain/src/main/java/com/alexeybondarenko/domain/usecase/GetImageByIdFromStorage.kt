package com.alexeybondarenko.domain.usecase

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.ImageStorageService

class GetImageByIdFromStorage(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(id: String): ImageEntity {
        return imageStorageService.getImageById(id)
    }
}