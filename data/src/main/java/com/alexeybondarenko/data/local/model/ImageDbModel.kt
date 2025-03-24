package com.alexeybondarenko.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
class ImageDbModel(
    @PrimaryKey
    val id: String,
    // ...
)