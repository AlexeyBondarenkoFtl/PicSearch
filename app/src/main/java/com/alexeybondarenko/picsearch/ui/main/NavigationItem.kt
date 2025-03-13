package com.alexeybondarenko.picsearch.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.alexeybondarenko.picsearch.ui.utils.navigation.Routes

// todo change icons
// todo move text to res
enum class NavigationItem(
    val label: String,
    val selectIcon: ImageVector,
    val unselectIcon: ImageVector,
    val associatedRoute: Routes,
) {
    SEARCH(
        "Поиск",
        Icons.Filled.Search,
        Icons.Outlined.Search,
        Routes.Search,
    ),

    SAVED(
        "Сохраненные",
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder,
        Routes.Saved,
    ),

    SETTINGS(
        "Настройки",
        Icons.Filled.Settings,
        Icons.Outlined.Settings,
        Routes.Settings,
    );
}