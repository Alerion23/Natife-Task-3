package com.wenger.natifetask3.ui.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wenger.natifetask3.domain.UserRepository

class UserListViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == UserListViewModel::class.java) {
            return UserListViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}