package com.alexeybondarenko.domain.usecase.settingsservice

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.SettingKeyEntity
import com.alexeybondarenko.domain.repository.SettingsService

class SetApiSettingUseCase(
    private val settingsService: SettingsService,
) {
    fun execute(newApiSettingValue: ApiSettingEntity) {
        settingsService.saveSetting(
            key = SettingKeyEntity.API,
            value = newApiSettingValue,
        )
    }
}