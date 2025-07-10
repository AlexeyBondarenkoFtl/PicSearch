package com.alexeybondarenko.picsearch.ui.utils.base.error


class PicSearchError(
    val message: String?,
) {
    constructor(e: Exception) : this(
        message = e.message,
    )
}