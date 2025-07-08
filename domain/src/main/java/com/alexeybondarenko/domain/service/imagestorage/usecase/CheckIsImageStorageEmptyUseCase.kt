package com.alexeybondarenko.domain.service.imagestorage.usecase

import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService

class CheckIsImageStorageEmptyUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(): Boolean {
        return imageStorageService.isStorageEmpty()
    }
}