package com.alexeybondarenko.domain.model

open class ImageEntity(
    open val id: String?,
    open val width: Int?,
    open val height: Int?,
    open val description: String?,
    open val user: UserEntity?,
    open val urls: ImageUrlsEntity?,
)

open class UserEntity(
    open val name: String?,
    open val profileImage: ProfileImageEntity?,
)

open class ImageUrlsEntity(
    open val full: String?,
    open val regular: String?,
)

open class ProfileImageEntity(
    open val small: String?,
)