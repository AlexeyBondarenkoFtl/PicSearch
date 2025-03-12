package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.PostEntity

interface PostsService {
    suspend fun getPosts(): List<PostEntity>?
}