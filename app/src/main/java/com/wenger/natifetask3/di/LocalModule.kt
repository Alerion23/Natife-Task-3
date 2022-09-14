package com.wenger.natifetask3.di

import android.content.Context
import androidx.room.Room
import com.wenger.natifetask3.data.UsersDatabase
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.data.managers.DataManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "user database"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideDataManager(database: UsersDatabase): DataManager {
        return DataManagerImpl(database)
    }

}