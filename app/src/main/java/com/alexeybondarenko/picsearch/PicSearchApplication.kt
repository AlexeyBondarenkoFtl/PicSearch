package com.alexeybondarenko.picsearch

import android.app.Application
import com.alexeybondarenko.picsearch.di.appModule
import com.alexeybondarenko.picsearch.di.appSettingsModule
import com.alexeybondarenko.picsearch.di.databaseModule
import com.alexeybondarenko.picsearch.di.imageSearchModule
import com.alexeybondarenko.picsearch.di.imageSearchScreenModule
import com.alexeybondarenko.picsearch.di.imageStorageModule
import com.alexeybondarenko.picsearch.di.savedImagesScreenModule
import com.alexeybondarenko.picsearch.di.searchHistoryModule
import com.alexeybondarenko.picsearch.di.settingScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class PicSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicSearchApplication)
            modules(
                appModule,
                imageSearchScreenModule,
                savedImagesScreenModule,
                settingScreenModule,
                imageSearchModule,
                imageStorageModule,
                searchHistoryModule,
                appSettingsModule,
                databaseModule
            )
        }
    }
}