package com.alexeybondarenko.domain.usecase

import com.alexeybondarenko.domain.model.SearchResultsEntity
import com.alexeybondarenko.domain.repository.PhotosService

class GetPhotosByQueryUseCase(
    private val photosService: PhotosService
) {
    suspend fun execute(query: String): SearchResultsEntity {
        return photosService.getPhotosByQuery(query)
    }
}