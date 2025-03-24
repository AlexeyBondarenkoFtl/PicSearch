package com.alexeybondarenko.picsearch.ui.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchRoute
import com.alexeybondarenko.picsearch.ui.savedimages.SavedImagesRoute
import com.alexeybondarenko.picsearch.ui.settings.SettingsScreen

@Composable
fun PicSearchNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Search,
        modifier = modifier,
    ) {
        composable<Routes.Search> {
            ImageSearchRoute()
        }

        composable<Routes.Saved> {
            SavedImagesRoute()
        }

        composable<Routes.Settings> {
            SettingsScreen()
        }
    }
}