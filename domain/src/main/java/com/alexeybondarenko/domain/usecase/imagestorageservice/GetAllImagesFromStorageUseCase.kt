package com.alexeybondarenko.domain.usecase.imagestorageservice

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.ImageStorageService

class GetAllImagesFromStorageUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(): List<ImageEntity> {
        return imageStorageService.getAllImages()
    }
}