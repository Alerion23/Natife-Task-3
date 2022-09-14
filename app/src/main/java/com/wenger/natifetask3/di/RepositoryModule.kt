package com.wenger.natifetask3.di

import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.domain.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<UserRepository> {
        UserRepositoryImpl(api = get(), dataManager = get())
    }

}