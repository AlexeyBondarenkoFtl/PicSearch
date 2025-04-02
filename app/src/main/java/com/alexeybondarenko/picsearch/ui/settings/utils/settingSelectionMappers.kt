package com.alexeybondarenko.picsearch.ui.settings.utils

import com.alexeybondarenko.domain.model.ApiSettingEntity
import com.alexeybondarenko.domain.model.LanguageSettingEntity
import com.alexeybondarenko.domain.model.ThemeSettingEntity
import com.alexeybondarenko.domain.utils.Mapper
import com.alexeybondarenko.picsearch.ui.settings.ApiSelection
import com.alexeybondarenko.picsearch.ui.settings.LanguageSelection
import com.alexeybondarenko.picsearch.ui.settings.ThemeSelection

class ApiSelectionMapper: Mapper<ApiSelection,ApiSettingEntity >() {
    override fun mapToEntity(from: ApiSelection): ApiSettingEntity {
        return when(from){
            ApiSelection.UNSPLASH -> ApiSettingEntity.UNSPLASH
            ApiSelection.PINTEREST -> ApiSettingEntity.PINTEREST
        }
    }

    override fun mapFromEntity(from: ApiSettingEntity): ApiSelection {
        return when (from) {
            ApiSettingEntity.UNSPLASH -> ApiSelection.UNSPLASH
            ApiSettingEntity.PINTEREST -> ApiSelection.PINTEREST
        }
    }
}

class ThemeSelectionMapper: Mapper<ThemeSelection,ThemeSettingEntity >() {
    override fun mapToEntity(from: ThemeSelection): ThemeSettingEntity {
        return when(from){
            ThemeSelection.LIGHT -> ThemeSettingEntity.LIGHT
            ThemeSelection.DARK -> ThemeSettingEntity.DARK
            ThemeSelection.SYSTEM -> ThemeSettingEntity.SYSTEM
        }
    }

    override fun mapFromEntity(from: ThemeSettingEntity): ThemeSelection {
        return when (from) {
            ThemeSettingEntity.LIGHT -> ThemeSelection.LIGHT
            ThemeSettingEntity.DARK -> ThemeSelection.DARK
            ThemeSettingEntity.SYSTEM -> ThemeSelection.SYSTEM
        }
    }
}

class LanguageSelectionMapper: Mapper<LanguageSelection,LanguageSettingEntity >() {
    override fun mapToEntity(from: LanguageSelection): LanguageSettingEntity {
        return when(from){
            LanguageSelection.RU -> LanguageSettingEntity.RU
            LanguageSelection.EN -> LanguageSettingEntity.EN
        }
    }

    override fun mapFromEntity(from: LanguageSettingEntity): LanguageSelection {
        return when (from) {
            LanguageSettingEntity.RU -> LanguageSelection.RU
            LanguageSettingEntity.EN -> LanguageSelection.EN
        }
    }
}