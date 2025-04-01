package com.alexeybondarenko.picsearch.di

import com.alexeybondarenko.data.local.PicSearchDatabase
import com.alexeybondarenko.data.local.dao.ImageDao
import com.alexeybondarenko.data.local.dao.SearchHistoryDao
import com.alexeybondarenko.data.remote.ApiServiceFactory
import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.repository.ImageStorageServiceImpl
import com.alexeybondarenko.data.repository.PhotosServiceUnsplashImpl
import com.alexeybondarenko.data.repository.SearchHistoryServiceImpl
import com.alexeybondarenko.data.repository.SettingsServiceImpl
import com.alexeybondarenko.domain.repository.*
import com.alexeybondarenko.domain.usecase.imagestorageservice.DeleteAllImagesInStorageUseCase
import com.alexeybondarenko.domain.usecase.imagestorageservice.DeleteImageByIdFromStorageUseCase
import com.alexeybondarenko.domain.usecase.imagestorageservice.GetAllImagesFromStorageUseCase
import com.alexeybondarenko.domain.usecase.imagestorageservice.GetImageByIdFromStorageUseCase
import com.alexeybondarenko.domain.usecase.imagestorageservice.SaveImageToStorageUseCase
import com.alexeybondarenko.domain.usecase.photoservice.GetPhotoByIdUseCase
import com.alexeybondarenko.domain.usecase.photoservice.GetPhotosByQueryUseCase
import com.alexeybondarenko.domain.usecase.searchhistoryservice.GetAllSearchHistoryEntriesUseCase
import com.alexeybondarenko.domain.usecase.searchhistoryservice.SaveQueryToSearchHistoryUseCase
import com.alexeybondarenko.domain.usecase.settingsservice.GetApiSettingUseCase
import com.alexeybondarenko.domain.usecase.settingsservice.GetLanguageSettingUseCase
import com.alexeybondarenko.domain.usecase.settingsservice.GetThemeSettingUseCase
import com.alexeybondarenko.domain.usecase.settingsservice.SetApiSettingUseCase
import com.alexeybondarenko.domain.usecase.settingsservice.SetLanguageSettingUseCase
import com.alexeybondarenko.domain.usecase.settingsservice.SetThemeSettingUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchViewModel
import com.alexeybondarenko.picsearch.ui.savedimages.SavedImagesViewModel
import com.alexeybondarenko.picsearch.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

}

val imageSearchScreenModule = module {
    viewModelOf(::ImageSearchViewModel)
}

val savedImagesScreenModule = module {
    viewModelOf(::SavedImagesViewModel)
}

val settingScreenModule = module {
    viewModelOf(::SettingsViewModel)
}

val imageSearchModule = module {
    singleOf(::GetPhotosByQueryUseCase)
    singleOf(::GetPhotoByIdUseCase)

    single<PhotosService> { PhotosServiceUnsplashImpl(get()) }

    // todo заменить на factory реализацию с выбором апи в зависимости от настроек
    single<UnsplashApi> { ApiServiceFactory.makeUnsplashApi() }
}

val imageStorageModule = module {
    singleOf(::DeleteAllImagesInStorageUseCase)
    singleOf(::DeleteImageByIdFromStorageUseCase)
    singleOf(::GetAllImagesFromStorageUseCase)
    singleOf(::GetImageByIdFromStorageUseCase)
    singleOf(::SaveImageToStorageUseCase)

    single<ImageStorageService> { ImageStorageServiceImpl(get()) }

    single<ImageDao> {
        val dataBase = get<PicSearchDatabase>()
        dataBase.imageDao()
    }
}

val searchHistoryModule = module {
    singleOf(::GetAllSearchHistoryEntriesUseCase)
    singleOf(::SaveQueryToSearchHistoryUseCase)

    single<SearchHistoryService> { SearchHistoryServiceImpl(get()) }

    single<SearchHistoryDao> {
        val dataBase = get<PicSearchDatabase>()
        dataBase.searchHistoryDao()
    }
}

val appSettingsModule = module {
    singleOf(::GetApiSettingUseCase)
    singleOf(::GetLanguageSettingUseCase)
    singleOf(::GetThemeSettingUseCase)
    singleOf(::SetApiSettingUseCase)
    singleOf(::SetThemeSettingUseCase)
    singleOf(::SetLanguageSettingUseCase)

    single<SettingsService> { SettingsServiceImpl(androidContext()) }
}

val databaseModule = module {
    single<PicSearchDatabase> { PicSearchDatabase.getInstance(androidContext()) }
}

