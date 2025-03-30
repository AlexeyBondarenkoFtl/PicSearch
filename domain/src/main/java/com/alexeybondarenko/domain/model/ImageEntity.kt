package com.alexeybondarenko.domain.model

import java.util.Date

data class ImageEntity(
    val id: String,
    val url: String,
    val description: String,
    val author: String,
    val savedDate: Date,
)