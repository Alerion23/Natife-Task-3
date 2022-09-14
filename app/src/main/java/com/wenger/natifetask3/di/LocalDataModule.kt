package com.wenger.natifetask3.di

import androidx.room.Room
import com.wenger.natifetask3.data.UsersDatabase
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.data.managers.DataManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localDataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            UsersDatabase::class.java,
            "user database"
        ).allowMainThreadQueries().build()
    }

    factory<DataManager> {
        DataManagerImpl(database = get())
    }

}