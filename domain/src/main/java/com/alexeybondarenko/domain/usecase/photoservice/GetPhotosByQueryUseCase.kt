package com.alexeybondarenko.domain.usecase.photoservice

import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.model.UnsplashSearchResultsEntity
import com.alexeybondarenko.domain.repository.PhotosService

class GetPhotosByQueryUseCase(
    private val photosService: PhotosService
) {
    suspend fun execute(query: String): List<ImageEntity>? {
        return photosService.getPhotosByQuery(query)
    }
}