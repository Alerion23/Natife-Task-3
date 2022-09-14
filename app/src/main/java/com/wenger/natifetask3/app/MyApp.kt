package com.wenger.natifetask3.app

import android.app.Application
import com.wenger.natifetask3.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(localDataModule, networkModule, repositoryModule, viewModels))
        }
    }
}