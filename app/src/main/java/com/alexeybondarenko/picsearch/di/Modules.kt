package com.alexeybondarenko.picsearch.di

import com.alexeybondarenko.data.remote.ApiServiceFactory
import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.repository.PhotosServiceUnsplashImpl
import com.alexeybondarenko.domain.repository.PhotosService
import com.alexeybondarenko.domain.usecase.GetPhotosByQueryUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchViewModel
import com.alexeybondarenko.picsearch.ui.savedimages.SavedImagesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
//    single { SomeShit(androidContext()) }

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