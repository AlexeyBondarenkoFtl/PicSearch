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
import com.alexeybondarenko.domain.service.imagestorage.repository.ImageStorageService
import com.alexeybondarenko.domain.service.photo.repository.PhotosService
import com.alexeybondarenko.domain.service.imagestorage.usecase.CheckIsImageStorageEmptyUseCase
import com.alexeybondarenko.domain.service.imagestorage.usecase.DeleteAllImagesInStorageUseCase
import com.alexeybondarenko.domain.service.imagestorage.usecase.DeleteImageByIdFromStorageUseCase
import com.alexeybondarenko.domain.service.imagestorage.usecase.GetAllImagesFromStorageUseCase
import com.alexeybondarenko.domain.service.imagestorage.usecase.GetImageByIdFromStorageUseCase
import com.alexeybondarenko.domain.service.imagestorage.usecase.SaveImageToStorageUseCase
import com.alexeybondarenko.domain.service.photo.usecase.GetPhotoByIdUseCase
import com.alexeybondarenko.domain.service.photo.usecase.GetPhotosByQueryUseCase
import com.alexeybondarenko.domain.service.searchhistory.repository.SearchHistoryService
import com.alexeybondarenko.domain.service.searchhistory.usecase.GetAllSearchHistoryEntriesUseCase
import com.alexeybondarenko.domain.service.searchhistory.usecase.SaveQueryToSearchHistoryUseCase
import com.alexeybondarenko.domain.service.settings.repository.SettingsService
import com.alexeybondarenko.domain.service.settings.usecase.GetApiSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.GetLanguageSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.GetThemeSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.SetApiSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.SetLanguageSettingUseCase
import com.alexeybondarenko.domain.service.settings.usecase.SetThemeSettingUseCase
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
    singleOf(::CheckIsImageStorageEmptyUseCase)
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

