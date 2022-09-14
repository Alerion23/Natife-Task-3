package com.wenger.natifetask3.di

import com.wenger.natifetask3.ui.fragments.info.UserInfoFragment
import com.wenger.natifetask3.ui.fragments.list.UserListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.jetbrains.annotations.Contract

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun bindUserInfoFragment(): UserInfoFragment
}