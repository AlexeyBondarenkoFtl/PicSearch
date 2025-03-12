package com.alexeybondarenko.data.remote

import com.alexeybondarenko.data.remote.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface TestJsonPlaceholderApi {
    @GET("/posts")
    suspend fun getPosts(): Response<List<PostResponse>>
}