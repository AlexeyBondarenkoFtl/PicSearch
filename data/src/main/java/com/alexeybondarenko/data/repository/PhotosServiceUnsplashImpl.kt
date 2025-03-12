package com.alexeybondarenko.data.repository

import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.remote.response.SearchResultsResponse
import com.alexeybondarenko.domain.model.SearchResultsEntity
import com.alexeybondarenko.domain.repository.PhotosService

class PhotosServiceUnsplashImpl(
    private val unsplashApi: UnsplashApi,
) : PhotosService {
    override suspend fun getPhotosByQuery(query: String): SearchResultsEntity {
        val result = unsplashApi.getPhotosByQuery(query)

        return SearchResultsResponse.EntityMapper().mapToEntity(result)
    }
}