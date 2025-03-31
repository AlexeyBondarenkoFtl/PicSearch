package com.alexeybondarenko.picsearch.ui.utils

object ImageUtils {
    fun calculateAspectRatio(
        width: Int?,
        height: Int?,
    ): Float {
        return when {
            height == null || width == null -> 1f
            height == 0 || width == 0 -> 1f
            else -> width.toFloat() / height.toFloat()
        }
    }
}