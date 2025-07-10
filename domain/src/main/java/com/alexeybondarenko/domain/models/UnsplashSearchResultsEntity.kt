package com.alexeybondarenko.domain.models

data class UnsplashSearchResultsEntity(
    val total: Int?,
    val totalPages: Int?,
    val results: List<UnsplashResultImageEntity>?
)

data class UnsplashResultImageEntity(
    override val id: String?,
    val createdAt: String?,
    override val width: Int?,
    override val height: Int?,
    val color: String?,
    val blurHash: String?,
    val likes: Int?,
    val likedByUser: Boolean?,
    override val description: String?,
    override val user: UnsplashUserEntity?,
    val currentUserCollections: List<Any>?,
    override val urls: UnsplashResultImageUrlsEntity?,
    val links: UnsplashResultImageLinksEntity?
) : ImageEntity(id, width, height, description, user, urls)

data class UnsplashUserEntity(
    val id: String?,
    val username: String?,
    override val name: String?,
    val firstName: String?,
    val lastName: String?,
    val instagramUsername: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    override val profileImage: UnsplashProfileImageEntity?,
    val links: UnsplashUserLinksEntity?
) : UserEntity(name, profileImage)

data class UnsplashResultImageUrlsEntity(
    val raw: String?,
    override val full: String?,
    override val regular: String?,
    val small: String?,
    val thumb: String?
) : ImageUrlsEntity(full, regular)

data class UnsplashResultImageLinksEntity(
    val self: String?,
    val html: String?,
    val download: String?
)

data class UnsplashUserLinksEntity(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?
)

data class UnsplashProfileImageEntity(
    override val small: String?,
    val medium: String?,
    val large: String?
) : ProfileImageEntity(small)
