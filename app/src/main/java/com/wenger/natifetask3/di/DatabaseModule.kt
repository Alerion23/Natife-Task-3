package com.wenger.natifetask3.di

import android.content.Context
import androidx.room.Room
import com.wenger.natifetask3.data.UsersDatabase
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.data.managers.DataManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@AppContext context: Context): UsersDatabase {
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