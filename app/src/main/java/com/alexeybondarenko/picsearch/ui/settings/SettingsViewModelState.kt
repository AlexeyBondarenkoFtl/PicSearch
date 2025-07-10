package com.alexeybondarenko.picsearch.ui.settings

import com.alexeybondarenko.domain.service.settings.repository.SettingsService
import com.alexeybondarenko.picsearch.ui.settings.utils.ApiSelectionMapper
import com.alexeybondarenko.picsearch.ui.settings.utils.LanguageSelectionMapper
import com.alexeybondarenko.picsearch.ui.settings.utils.ThemeSelectionMapper
import com.alexeybondarenko.picsearch.ui.utils.base.error.PicSearchError

data class SettingsViewModelState(
    val currentApi: ApiSelection = api,
    val currentTheme: ThemeSelection = theme,
    val currentLanguage: LanguageSelection = language,
    val isDeleteAllImagesPossible: Boolean = false,
    val error: PicSearchError? = null,
) {
    fun toUiState(): SettingsUiState = SettingsUiState.SettingsLoaded(
        currentApi = currentApi,
        currentTheme = currentTheme,
        currentLanguage = currentLanguage,
        isDeleteAllImagesPossible = isDeleteAllImagesPossible,
        operationErrorMessage = error,
    )

    companion object DefaultSettings {
        private val apiMapper = ApiSelectionMapper()
        private val themeMapper = ThemeSelectionMapper()
        private val languageMapper = LanguageSelectionMapper()

        private val api = apiMapper.mapFromEntity(SettingsService.DefaultSettings.apiDefault)
        private val theme = themeMapper.mapFromEntity(SettingsService.DefaultSettings.themeDefault)
        private val language =
            languageMapper.mapFromEntity(SettingsService.DefaultSettings.languageDefault)
    }
}

sealed interface SettingsUiState {
    data class SettingsLoaded(
        val currentApi: ApiSelection,
        val currentTheme: ThemeSelection,
        val currentLanguage: LanguageSelection,
        val isDeleteAllImagesPossible: Boolean,
        val operationErrorMessage: PicSearchError?,
    ) : SettingsUiState
}

interface Selection {
    val title: String
}

enum class ApiSelection(override val title: String) : Selection {
    UNSPLASH("Unsplash"),
    PINTEREST("Pinterest");
}

enum class ThemeSelection(override val title: String) : Selection {
    LIGHT("Светлая"),
    DARK("Темная"),
    SYSTEM("Системная");
}

enum class LanguageSelection(override val title: String) : Selection {
    RU("RU"),
    EN("EN");
}
