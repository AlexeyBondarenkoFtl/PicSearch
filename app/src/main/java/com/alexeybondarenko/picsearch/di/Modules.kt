package com.alexeybondarenko.picsearch.di

import com.alexeybondarenko.data.repository.SecondRepositoryImpl
import com.alexeybondarenko.data.repository.UserRepositoryImpl
import com.alexeybondarenko.domain.repository.SecondRepository
import com.alexeybondarenko.domain.repository.UserRepository
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single<SecondRepository> { SecondRepositoryImpl(get()) }



//    single { SomeShit(androidContext()) }
}

val imageSearchModel = module {
    viewModel { ImageSearchViewModel(get(), get()) }
}