package com.wenger.natifetask3.di

import android.content.Context
import com.wenger.natifetask3.app.MyApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    @Inject
    @AppContext
    fun provideContext(): Context = context
}