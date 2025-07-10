package com.alexeybondarenko.data.remote.response

import com.alexeybondarenko.domain.models.UnsplashProfileImageEntity
import com.alexeybondarenko.domain.models.UnsplashResultImageEntity
import com.alexeybondarenko.domain.models.UnsplashResultImageLinksEntity
import com.alexeybondarenko.domain.models.UnsplashResultImageUrlsEntity
import com.alexeybondarenko.domain.models.UnsplashSearchResultsEntity
import com.alexeybondarenko.domain.models.UnsplashUserEntity
import com.alexeybondarenko.domain.models.UnsplashUserLinksEntity

data class UnsplashSearchResultsResponse(
    val total: Int?,
    val totalPages: Int?,
    val results: List<UnsplashResultImageResponse>?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashSearchResultsResponse): UnsplashSearchResultsEntity {
            return UnsplashSearchResultsEntity(
                total = from.total,
                totalPages = from.totalPages,
                results = from.results?.map { UnsplashResultImageResponse.EntityMapper().mapToEntity(it) } ?: emptyList()
            )
        }
    }
}

data class UnsplashResultImageResponse(
    val id: String?,
    val createdAt: String?,
    val width: Int?,
    val height: Int?,
    val color: String?,
    val blurHash: String?,
    val likes: Int?,
    val likedByUser: Boolean?,
    val description: String?,
    val user: UnsplashUserResponse?,
    val currentUserCollections: List<Any>?,
    val urls: UnsplashResultImageUrlsResponse?,
    val links: UnsplashResultImageLinksResponse?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashResultImageResponse): UnsplashResultImageEntity {
            return UnsplashResultImageEntity(
                id = from.id,
                createdAt = from.createdAt,
                width = from.width,
                height = from.height,
                color = from.color,
                blurHash = from.blurHash,
                likes = from.likes,
                likedByUser = from.likedByUser,
                description = from.description,
                user = from.user?.let { UnsplashUserResponse.EntityMapper().mapToEntity(it) },
                currentUserCollections = from.currentUserCollections,
                urls = from.urls?.let { UnsplashResultImageUrlsResponse.EntityMapper().mapToEntity(it) },
                links = from.links?.let { UnsplashResultImageLinksResponse.EntityMapper().mapToEntity(it) },
            )
        }
    }
}

data class UnsplashUserResponse(
    val id: String?,
    val username: String?,
    val name: String?,
    val firstName: String?,
    val lastName: String?,
    val instagramUsername: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val profileImage: UnsplashProfileImageResponse?,
    val links: UnsplashUserLinksResponse?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashUserResponse): UnsplashUserEntity {
            return UnsplashUserEntity(
                id = from.id,
                username = from.username,
                name = from.name,
                firstName = from.firstName,
                lastName = from.lastName,
                instagramUsername = from.instagramUsername,
                twitterUsername = from.twitterUsername,
                portfolioUrl = from.portfolioUrl,
                profileImage = from.profileImage?.let { UnsplashProfileImageResponse.EntityMapper().mapToEntity(it) },
                links = from.links?.let { UnsplashUserLinksResponse.EntityMapper().mapToEntity(it) },
            )
        }
    }
}

data class UnsplashResultImageUrlsResponse(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashResultImageUrlsResponse): UnsplashResultImageUrlsEntity {
            return UnsplashResultImageUrlsEntity(
                raw = from.raw,
                full = from.full,
                regular = from.regular,
                small = from.small,
                thumb = from.thumb,
            )
        }
    }
}

data class UnsplashResultImageLinksResponse(
    val self: String?,
    val html: String?,
    val download: String?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashResultImageLinksResponse): UnsplashResultImageLinksEntity {
            return UnsplashResultImageLinksEntity(
                self = from.self,
                html = from.html,
                download = from.download,
            )
        }
    }
}

data class UnsplashUserLinksResponse(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashUserLinksResponse): UnsplashUserLinksEntity {
            return UnsplashUserLinksEntity(
                self = from.self,
                html = from.html,
                photos = from.photos,
                likes = from.likes,
            )
        }
    }
}

data class UnsplashProfileImageResponse(
    val small: String?,
    val medium: String?,
    val large: String?
) {
    class EntityMapper {
        fun mapToEntity(from: UnsplashProfileImageResponse): UnsplashProfileImageEntity {
            return UnsplashProfileImageEntity(
                small = from.small,
                medium = from.medium,
                large = from.large
            )
        }
    }
}