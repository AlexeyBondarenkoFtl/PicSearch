package com.alexeybondarenko.picsearch

import android.app.Application
import com.alexeybondarenko.picsearch.di.appModule
import com.alexeybondarenko.picsearch.di.appSettingsServiceModule
import com.alexeybondarenko.picsearch.di.databaseModule
import com.alexeybondarenko.picsearch.di.imageSearchServiceModule
import com.alexeybondarenko.picsearch.di.imageSearchScreenModule
import com.alexeybondarenko.picsearch.di.imageStorageServiceModule
import com.alexeybondarenko.picsearch.di.savedImagesScreenModule
import com.alexeybondarenko.picsearch.di.searchHistoryModule
import com.alexeybondarenko.picsearch.di.searchHistoryServiceModule
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
                searchHistoryModule,
                imageSearchServiceModule,
                imageStorageServiceModule,
                searchHistoryServiceModule,
                appSettingsServiceModule,
                databaseModule
            )
        }
    }
}