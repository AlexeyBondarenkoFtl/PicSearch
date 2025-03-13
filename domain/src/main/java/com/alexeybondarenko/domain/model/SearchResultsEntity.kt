package com.alexeybondarenko.domain.model

data class SearchResultsEntity(
    val total: Int?,
    val totalPages: Int?,
    val results: List<ResultImageEntity>?
)

data class ResultImageEntity(
    val id: String?,
    val createdAt: String?,
    val width: Int?,
    val height: Int?,
    val color: String?,
    val blurHash: String?,
    val likes: Int?,
    val likedByUser: Boolean?,
    val description: String?,
    val user: UserEntity?,
    val currentUserCollections: List<Any>?,
    val urls: ResultImageUrlsEntity?,
    val links: ResultImageLinksEntity?
)

data class UserEntity(
    val id: String?,
    val username: String?,
    val name: String?,
    val firstName: String?,
    val lastName: String?,
    val instagramUsername: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val profileImage: ProfileImageEntity?,
    val links: UserLinksEntity?
)

data class ResultImageUrlsEntity(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
)

data class ResultImageLinksEntity(
    val self: String?,
    val html: String?,
    val download: String?
)

data class UserLinksEntity(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?
)

data class ProfileImageEntity(
    val small: String?,
    val medium: String?,
    val large: String?
)
