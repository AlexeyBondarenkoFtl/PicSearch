package com.alexeybondarenko.domain.usecase

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.ImageStorageService

class SaveImageToStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(imageEntity: ImageEntity) {
        imageStorageService.saveImage(imageEntity)
    }
}