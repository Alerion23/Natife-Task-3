package com.wenger.natifetask3.ui.fragments.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class UserInfoViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == UserInfoViewModel::class.java) {
            return UserInfoViewModel() as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}