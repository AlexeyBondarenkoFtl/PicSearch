package com.alexeybondarenko.picsearch.ui.utils.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object Search : Routes

    @Serializable
    data object Saved : Routes

    @Serializable
    data object Settings : Routes

    fun getSimpleName(): String? {
        return this::class.simpleName
    }
}