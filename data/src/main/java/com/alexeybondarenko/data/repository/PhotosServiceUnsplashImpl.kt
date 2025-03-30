package com.alexeybondarenko.data.repository

import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.remote.response.UnsplashSearchResultsResponse
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.PhotosService

class PhotosServiceUnsplashImpl(
    private val unsplashApi: UnsplashApi,
) : PhotosService {
    override suspend fun getPhotosByQuery(query: String): List<ImageEntity>? {
        val mapper = UnsplashSearchResultsResponse.EntityMapper()

        val result = unsplashApi.getPhotosByQuery(query)
        val unsplashSearchResultEntity = mapper.mapToEntity(result)

        return unsplashSearchResultEntity.results?.map { it }
    }
}