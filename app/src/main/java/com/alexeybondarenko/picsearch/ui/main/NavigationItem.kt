package com.alexeybondarenko.picsearch.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

// todo change icons
// todo move text to res
enum class NavigationItem(
    val label: String,
    val selectIcon: ImageVector,
    val unselectIcon: ImageVector,
) {
    SEARCH(
        "Поиск",
        Icons.Filled.Search,
        Icons.Outlined.Search
    ),

    SAVED(
        "Сохраненные",
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder
    ),

    SETTINGS(
        "Настройки",
        Icons.Filled.Settings,
        Icons.Outlined.Settings
    );
}