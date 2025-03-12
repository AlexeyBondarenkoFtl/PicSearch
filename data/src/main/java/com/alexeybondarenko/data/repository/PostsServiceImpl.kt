package com.alexeybondarenko.data.repository

import com.alexeybondarenko.data.remote.TestJsonPlaceholderApi
import com.alexeybondarenko.data.remote.response.PostResponse
import com.alexeybondarenko.domain.model.PostEntity
import com.alexeybondarenko.domain.repository.PostsService

class PostsServiceImpl(
    private val api: TestJsonPlaceholderApi,
) : PostsService {
    override suspend fun getPosts(): List<PostEntity>? {
        try {
            val result = api.getPosts()

            return if (result.body() == null) {
                null
            } else {
                result.body()?.map { PostResponse.EntityMapper().mapToEntity(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}