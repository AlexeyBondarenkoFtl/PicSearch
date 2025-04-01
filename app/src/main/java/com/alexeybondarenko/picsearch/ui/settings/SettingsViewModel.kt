package com.alexeybondarenko.picsearch.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SettingsViewModel(

) : ViewModel() {
    private val viewModelState = MutableStateFlow(SettingsViewModelState())

    val uiState = viewModelState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value
        )

    fun selectApi(newApi: ApiSelection) {
        viewModelState.update { it.copy(currentApi = newApi) }
    }

    fun selectTheme(newTheme: ThemeSelection) {
        viewModelState.update { it.copy(currentTheme = newTheme) }
    }

    fun selectLanguage(newLanguage: LanguageSelection) {
        viewModelState.update { it.copy(currentLanguage = newLanguage) }
    }

    companion object {
        private const val TAG = "SettingsViewModel"
    }
}