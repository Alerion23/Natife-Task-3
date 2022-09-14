package com.wenger.natifetask3.ui.fragments.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.AssistedInject.*
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.domain.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserInfoViewModel @AssistedInject constructor(
    private val repository: UserRepository,
    @Assisted private val userId: String
) : ViewModel() {

    @AssistedFactory
    interface UserInfoFactory {
        fun create(userId: String): UserInfoViewModel
    }

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            _userInfo.value = repository.getUserById(userId)
            _isLoading.value = false
        }
    }
}