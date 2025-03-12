package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.SearchResultsEntity


interface PhotosService {
    suspend fun getPhotosByQuery(query: String): SearchResultsEntity
}