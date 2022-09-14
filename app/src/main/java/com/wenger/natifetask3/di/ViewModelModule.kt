package com.wenger.natifetask3.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wenger.natifetask3.ui.fragments.list.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel

}