package com.alexeybondarenko.domain.usecase

import com.alexeybondarenko.domain.model.PostEntity
import com.alexeybondarenko.domain.repository.PostsService

class GetFirstPostUseCase(
    private val postsService: PostsService,
) {
    suspend fun execute(): PostEntity? {
        val posts = postsService.getPosts() ?: return null

        if (posts.isEmpty()) return null

        return posts.first()
    }
}