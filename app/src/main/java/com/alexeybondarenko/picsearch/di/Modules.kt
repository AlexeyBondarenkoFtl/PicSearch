package com.alexeybondarenko.picsearch.di

import com.alexeybondarenko.data.local.PicSearchDatabase
import com.alexeybondarenko.data.local.dao.ImageDao
import com.alexeybondarenko.data.remote.ApiServiceFactory
import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.repository.ImageStorageServiceImpl
import com.alexeybondarenko.data.repository.PhotosServiceUnsplashImpl
import com.alexeybondarenko.domain.repository.*
import com.alexeybondarenko.domain.usecase.*
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchViewModel
import com.alexeybondarenko.picsearch.ui.savedimages.SavedImagesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

}

val imageSearchModule = module {
    // ViewModels
    viewModelOf(::ImageSearchViewModel)

    // Use cases
    single { GetPhotosByQueryUseCase(get()) }

    // Service
    single<PhotosService> { PhotosServiceUnsplashImpl(get()) }

    // Api
    single<UnsplashApi> { ApiServiceFactory.makeUnsplashApi() }
}

val savedImagesModule = module {
    viewModelOf(::SavedImagesViewModel)
}

val imageStorageModule = module {
    singleOf(::DeleteAllImagesInStorageUseCase)
    singleOf(::GetAllImagesFromStorage)
    singleOf(::GetImageByIdFromStorage)
    singleOf(::SaveImageToStorageUseCase)

    single<ImageStorageService> { ImageStorageServiceImpl(get()) }

    single<ImageDao> {
        val dataBase = get<PicSearchDatabase>()
        dataBase.imageDao()
    }

    single<PicSearchDatabase> { PicSearchDatabase.getInstance(androidContext()) }
}