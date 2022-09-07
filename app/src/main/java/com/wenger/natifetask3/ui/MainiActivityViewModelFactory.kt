package com.wenger.natifetask3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wenger.natifetask3.domain.UserRepository

class MainiActivityViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MainActivityViewModel::class.java) {
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}