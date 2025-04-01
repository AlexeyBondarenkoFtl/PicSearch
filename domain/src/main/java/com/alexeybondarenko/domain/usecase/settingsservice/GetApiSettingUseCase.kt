package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.repository.SettingsService

class GetApiSettingUseCase(
    private val settingsService: SettingsService,
) {
    fun execute() {
        settingsService.getSetting(
            key = SettingKeyEntity.API,
            defValue = ApiSettingEntity.UNSPLASH,
        )
    }
}