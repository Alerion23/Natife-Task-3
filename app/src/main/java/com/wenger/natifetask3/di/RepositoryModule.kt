package com.wenger.natifetask3.di

import com.wenger.natifetask3.api.ApiService
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: ApiService, dataManager: DataManager): UserRepository =
        UserRepositoryImpl(api, dataManager)
}