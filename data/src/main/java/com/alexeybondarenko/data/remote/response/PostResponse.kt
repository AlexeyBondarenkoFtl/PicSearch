package com.alexeybondarenko.data.remote.response

import com.alexeybondarenko.domain.model.PostEntity

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
) {
    class EntityMapper {
        fun mapToEntity(from: PostResponse): PostEntity {
            return PostEntity(
                from.userId,
                from.id,
                from.title,
                from.body
            )
        }
    }
}