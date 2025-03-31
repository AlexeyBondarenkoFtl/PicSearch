package com.alexeybondarenko.domain.usecase.photoservice

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.PhotosService

class GetPhotoByIdUseCase(
    private val photosService: PhotosService
) {
    suspend fun execute(id: String): ImageEntity? {
        return photosService.getPhotoById(id)
    }
}