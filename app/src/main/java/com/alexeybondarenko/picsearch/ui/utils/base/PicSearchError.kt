package com.alexeybondarenko.picsearch.ui.utils.base


class PicSearchError(
    val message: String?,
) {
    constructor(e: Exception) : this(
        message = e.message,
    )
}