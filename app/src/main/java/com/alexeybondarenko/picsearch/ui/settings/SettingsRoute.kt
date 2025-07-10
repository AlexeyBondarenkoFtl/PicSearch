package com.alexeybondarenko.picsearch.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onApiSelected = viewModel::selectApi,
        onThemeSelected = viewModel::selectTheme,
        onLanguageSelected = viewModel::selectLanguage,
        onDeleteAllImagesClick = viewModel::deleteAllImagesInStorage,
    )
}