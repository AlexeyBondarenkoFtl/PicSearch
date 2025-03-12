package com.alexeybondarenko.picsearch.ui.utils.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Search : Routes()

    @Serializable
    data object Saved : Routes()

    @Serializable
    data object Settings : Routes()
}