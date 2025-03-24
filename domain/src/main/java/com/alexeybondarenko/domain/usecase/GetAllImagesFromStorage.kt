package com.alexeybondarenko.domain.usecase

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.ImageStorageService

class GetAllImagesFromStorage(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(): List<ImageEntity> {
        return imageStorageService.getAllImages()
    }
}