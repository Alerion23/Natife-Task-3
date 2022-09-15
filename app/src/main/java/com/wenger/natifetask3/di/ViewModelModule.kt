package com.wenger.natifetask3.di

import com.wenger.natifetask3.ui.fragments.info.UserInfoViewModel
import com.wenger.natifetask3.ui.fragments.list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel {
        UserListViewModel(repository = get())
    }

    viewModel {
        UserInfoViewModel(repository = get(), userId = get())
    }

}