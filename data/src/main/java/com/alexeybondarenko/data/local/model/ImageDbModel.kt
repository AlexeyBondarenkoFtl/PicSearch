package com.alexeybondarenko.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "images")
class ImageDbModel(
    @PrimaryKey
    val id: String,
    val width: Int?,
    val height: Int?,
    val description: String?,

    val authorName: String?,
    val authorProfileImageUrl: String?,

    val urlFull: String?,
    val urlRegular: String?,

    // todo ЧТО С ЭТИМ ДЕЛАТЬ??
    val savedDate: String?,
)