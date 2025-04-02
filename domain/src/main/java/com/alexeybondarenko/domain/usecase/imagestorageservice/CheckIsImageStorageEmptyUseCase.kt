package com.alexeybondarenko.domain.usecase.imagestorageservice

import com.alexeybondarenko.domain.repository.ImageStorageService

class CheckIsImageStorageEmptyUseCase(
    private val imageStorageService: ImageStorageService
) {
    suspend fun execute(): Boolean {
        return imageStorageService.isStorageEmpty()
    }
}