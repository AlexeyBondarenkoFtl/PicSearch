package com.alexeybondarenko.data.local.mapper

import com.alexeybondarenko.data.local.model.ImageDbModel
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.model.ImageUrlsEntity
import com.alexeybondarenko.domain.model.ProfileImageEntity
import com.alexeybondarenko.domain.model.UserEntity
import com.alexeybondarenko.domain.utils.Mapper
import java.util.Date
import java.util.UUID

class ImageDbModelMapper : Mapper<ImageDbModel, ImageEntity>() {
    override fun mapToEntity(from: ImageDbModel): ImageEntity {
        return ImageEntity(
            id = from.id,
            width = from.width,
            height = from.height,
            description = from.description,
            user = UserEntity(
                name = from.authorName,
                profileImage = ProfileImageEntity(
                    small = from.authorProfileImageUrl
                ),
            ),
            urls = ImageUrlsEntity(
                full = from.urlFull,
                regular = from.urlRegular
            )
        )
    }

    override fun mapFromEntity(from: ImageEntity): ImageDbModel {
        return ImageDbModel(
            // todo rework
            id = from.id ?: UUID.randomUUID().toString(),
            width = from.width,
            height = from.height,
            description = from.description,
            authorName = from.user?.name,
            authorProfileImageUrl = from.user?.profileImage?.small,
            urlFull = from.urls?.full,
            urlRegular = from.urls?.regular,
            savedDate = Date().toString()
        )
    }
}