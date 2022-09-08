package com.wenger.natifetask3.ui.fragments.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wenger.natifetask3.domain.UserRepository
import java.lang.IllegalArgumentException

class UserInfoViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == UserInfoViewModel::class.java) {
            return UserInfoViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}