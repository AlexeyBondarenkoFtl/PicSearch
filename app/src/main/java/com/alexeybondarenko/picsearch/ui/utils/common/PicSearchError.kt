package com.alexeybondarenko.picsearch.ui.utils.common

open class PicSearchError(
    val message: String?,
)

class PicSearchErrorWithAction(
    message: String?,
    val confirmAction: (() -> Unit) = {},
    val dismissAction: (() -> Unit) = {},
) : PicSearchError(message)