package com.alexeybondarenko.data.repository

import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.remote.response.UnsplashResultImageResponse
import com.alexeybondarenko.data.remote.response.UnsplashSearchResultsResponse
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.PhotosService

class PhotosServiceUnsplashImpl(
    private val unsplashApi: UnsplashApi,
) : PhotosService {
    override suspend fun getPhotosByQuery(query: String, page: Int): List<ImageEntity>? {
        val mapper = UnsplashSearchResultsResponse.EntityMapper()

        val result = unsplashApi.getPhotosByQuery(query, page)
        val unsplashSearchResultEntity = mapper.mapToEntity(result)

        return unsplashSearchResultEntity.results?.map { it }
    }

    override suspend fun getPhotoById(id: String): ImageEntity? {
        val mapper = UnsplashResultImageResponse.EntityMapper()

        val result = unsplashApi.getPhotoById(id)

        return mapper.mapToEntity(result)
    }
}