package com.alexeybondarenko.data.remote.response

import com.alexeybondarenko.domain.model.ProfileImageEntity
import com.alexeybondarenko.domain.model.ResultImageEntity
import com.alexeybondarenko.domain.model.ResultImageLinksEntity
import com.alexeybondarenko.domain.model.ResultImageUrlsEntity
import com.alexeybondarenko.domain.model.SearchResultsEntity
import com.alexeybondarenko.domain.model.UserEntity
import com.alexeybondarenko.domain.model.UserLinksEntity

data class SearchResultsResponse(
    val total: Int?,
    val totalPages: Int?,
    val results: List<ResultImageResponse>?
) {
    class EntityMapper {
        fun mapToEntity(from: SearchResultsResponse): SearchResultsEntity {
            return SearchResultsEntity(
                total = from.total,
                totalPages = from.totalPages,
                results = from.results?.map { ResultImageResponse.EntityMapper().mapToEntity(it) } ?: emptyList()
            )
        }
    }
}

data class ResultImageResponse(
    val id: String?,
    val createdAt: String?,
    val width: Int?,
    val height: Int?,
    val color: String?,
    val blurHash: String?,
    val likes: Int?,
    val likedByUser: Boolean?,
    val description: String?,
    val user: UserResponse?,
    val currentUserCollections: List<Any>?,
    val urls: ResultImageUrlsResponse?,
    val links: ResultImageLinksResponse?
) {
    class EntityMapper {
        fun mapToEntity(from: ResultImageResponse): ResultImageEntity {
            return ResultImageEntity(
                id = from.id,
                createdAt = from.createdAt,
                width = from.width,
                height = from.height,
                color = from.color,
                blurHash = from.blurHash,
                likes = from.likes,
                likedByUser = from.likedByUser,
                description = from.description,
                user = from.user?.let { UserResponse.EntityMapper().mapToEntity(it) },
                currentUserCollections = from.currentUserCollections,
                urls = from.urls?.let { ResultImageUrlsResponse.EntityMapper().mapToEntity(it) },
                links = from.links?.let { ResultImageLinksResponse.EntityMapper().mapToEntity(it) },
            )
        }
    }
}

data class UserResponse(
    val id: String?,
    val username: String?,
    val name: String?,
    val firstName: String?,
    val lastName: String?,
    val instagramUsername: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val profileImage: ProfileImageResponse?,
    val links: UserLinksResponse?
) {
    class EntityMapper {
        fun mapToEntity(from: UserResponse): UserEntity {
            return UserEntity(
                id = from.id,
                username = from.username,
                name = from.name,
                firstName = from.firstName,
                lastName = from.lastName,
                instagramUsername = from.instagramUsername,
                twitterUsername = from.twitterUsername,
                portfolioUrl = from.portfolioUrl,
                profileImage = from.profileImage?.let { ProfileImageResponse.EntityMapper().mapToEntity(it) },
                links = from.links?.let { UserLinksResponse.EntityMapper().mapToEntity(it) },
            )
        }
    }
}

data class ResultImageUrlsResponse(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
) {
    class EntityMapper {
        fun mapToEntity(from: ResultImageUrlsResponse): ResultImageUrlsEntity {
            return ResultImageUrlsEntity(
                raw = from.raw,
                full = from.full,
                regular = from.regular,
                small = from.small,
                thumb = from.thumb,
            )
        }
    }
}

data class ResultImageLinksResponse(
    val self: String?,
    val html: String?,
    val download: String?
) {
    class EntityMapper {
        fun mapToEntity(from: ResultImageLinksResponse): ResultImageLinksEntity {
            return ResultImageLinksEntity(
                self = from.self,
                html = from.html,
                download = from.download,
            )
        }
    }
}

data class UserLinksResponse(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?
) {
    class EntityMapper {
        fun mapToEntity(from: UserLinksResponse): UserLinksEntity {
            return UserLinksEntity(
                self = from.self,
                html = from.html,
                photos = from.photos,
                likes = from.likes,
            )
        }
    }
}

data class ProfileImageResponse(
    val small: String?,
    val medium: String?,
    val large: String?
) {
    class EntityMapper {
        fun mapToEntity(from: ProfileImageResponse): ProfileImageEntity {
            return ProfileImageEntity(
                small = from.small,
                medium = from.medium,
                large = from.large
            )
        }
    }
}