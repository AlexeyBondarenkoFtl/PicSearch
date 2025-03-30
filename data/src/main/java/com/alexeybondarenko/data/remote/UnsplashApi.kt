package com.alexeybondarenko.data.remote

import com.alexeybondarenko.data.remote.response.UnsplashSearchResultsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/search/photos")
    suspend fun getPhotosByQuery(
        @Query("query") query: String,
//        @Query("page") page: String? = null,
    ): UnsplashSearchResultsResponse
}