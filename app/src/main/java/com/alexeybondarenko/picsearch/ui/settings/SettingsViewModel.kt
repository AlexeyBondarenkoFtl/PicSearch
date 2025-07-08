package com.alexeybondarenko.picsearch.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.domain.service.imagestorage.usecase.CheckIsImageStorageEmptyUseCase
import com.alexeybondarenko.domain.service.imagestorage.usecase.DeleteAllImagesInStorageUseCase
import com.alexeybondarenko.domain.service.settings.usecase.GetApiSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.GetLanguageSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.GetThemeSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.SetApiSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.SetLanguageSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.SetThemeSettingUseCase
import com.alexeybondarenko.picsearch.ui.settings.utils.ApiSelectionMapper
import com.alexeybondarenko.picsearch.ui.settings.utils.LanguageSelectionMapper
import com.alexeybondarenko.picsearch.ui.settings.utils.ThemeSelectionMapper
import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val setApiSettingUseCase: SetApiSettingUseCase,
    private val getApiSettingUseCase: GetApiSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
    private val setThemeSettingUseCase: SetThemeSettingUseCase,
    private val getLanguageSettingUseCase: GetLanguageSettingUseCase,
    private val setLanguageSettingUseCase: SetLanguageSettingUseCase,
    private val checkIsImageStorageEmptyUseCase: CheckIsImageStorageEmptyUseCase,
    private val deleteAllImagesInStorageUseCase: DeleteAllImagesInStorageUseCase,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(SettingsViewModelState())

    val uiState = viewModelState
        .map(SettingsViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toUiState()
        )

    private val apiMapper = ApiSelectionMapper()
    private val themeMapper = ThemeSelectionMapper()
    private val languageMapper = LanguageSelectionMapper()

    init {
        setSavedSettingValues()
    }

    fun selectApi(newApi: ApiSelection) {
        val entity = apiMapper.mapToEntity(newApi)
        setApiSettingUseCase.execute(entity)

        viewModelState.update { it.copy(currentApi = newApi) }
    }

    fun selectTheme(newTheme: ThemeSelection) {
        val entity = themeMapper.mapToEntity(newTheme)
        setThemeSettingUseCase.execute(entity)

        viewModelState.update { it.copy(currentTheme = newTheme) }
    }

    fun selectLanguage(newLanguage: LanguageSelection) {
        val entity = languageMapper.mapToEntity(newLanguage)
        setLanguageSettingUseCase.execute(entity)

        viewModelState.update { it.copy(currentLanguage = newLanguage) }
    }

    fun deleteAllImagesInStorage() {
        viewModelScope.launch {
            try {
                deleteAllImagesInStorageUseCase.execute()

                viewModelState.update {
                    it.copy(
                        isDeleteAllImagesPossible = !checkIsImageStorageEmptyUseCase.execute()
                    )
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun setSavedSettingValues() {
        viewModelScope.launch {
            try {
                viewModelState.update {
                    it.copy(
                        currentApi = apiMapper.mapFromEntity(getApiSettingUseCase.execute()),
                        currentTheme = themeMapper.mapFromEntity(getThemeSettingUseCase.execute()),
                        currentLanguage = languageMapper.mapFromEntity(getLanguageSettingUseCase.execute()),
                        isDeleteAllImagesPossible = !checkIsImageStorageEmptyUseCase.execute()
                    )
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun handleException(e: Exception) {
        viewModelState.update {
            it.copy(error = PicSearchError(e))
        }
    }

    companion object {
        private const val TAG = "SettingsViewModel"
    }
}