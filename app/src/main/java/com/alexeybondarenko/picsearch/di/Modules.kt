package com.alexeybondarenko.picsearch.di

import com.alexeybondarenko.data.remote.ApiServiceFactory
import com.alexeybondarenko.data.remote.TestJsonPlaceholderApi
import com.alexeybondarenko.data.remote.UnsplashApi
import com.alexeybondarenko.data.repository.PhotosServiceUnsplashImpl
import com.alexeybondarenko.data.repository.PostsServiceImpl
import com.alexeybondarenko.domain.repository.PhotosService
import com.alexeybondarenko.domain.repository.PostsService
import com.alexeybondarenko.domain.usecase.GetFirstPostUseCase
import com.alexeybondarenko.domain.usecase.GetPhotosByQueryUseCase
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//    single<UserRepository> { UserRepositoryImpl() }
//    single<SecondRepository> { SecondRepositoryImpl(get()) }


//    single { SomeShit(androidContext()) }

    viewModel { ImageSearchViewModel(get(), get()) }
}

val imageSearchModule = module {
    single { GetPhotosByQueryUseCase(get()) }
    single<PhotosService> { PhotosServiceUnsplashImpl(get()) }
    single<UnsplashApi> { ApiServiceFactory.makeUnsplashApi() }
}

val postsModule = module {
    single { GetFirstPostUseCase(get()) }
    single<PostsService> { PostsServiceImpl(get()) }
    single<TestJsonPlaceholderApi> { ApiServiceFactory.makeTestJsonPlaceholderApi() }
}