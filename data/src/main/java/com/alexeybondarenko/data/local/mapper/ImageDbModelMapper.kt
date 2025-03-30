package com.alexeybondarenko.data.local.mapper

import com.alexeybondarenko.data.local.model.ImageDbModel
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.utils.Mapper

class ImageDbModelMapper : Mapper<ImageDbModel, ImageEntity>() {
    override fun mapToEntity(from: ImageDbModel): ImageEntity {
        return ImageEntity(
            id = from.id,
            url = from.url,
            description = from.description,
            author = from.author,
            savedDate = from.savedDate,
        )
    }

    override fun mapFromEntity(from: ImageEntity): ImageDbModel {
        return ImageDbModel(
            id = from.id,
            url = from.url,
            description = from.description,
            author = from.author,
            savedDate = from.savedDate,
        )
    }
}