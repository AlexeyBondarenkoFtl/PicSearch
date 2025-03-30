package com.alexeybondarenko.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "images")
class ImageDbModel(
    @PrimaryKey
    val id: String,
    val url: String,
    val description: String,
    val author: String,
    val savedDate: Date,
)