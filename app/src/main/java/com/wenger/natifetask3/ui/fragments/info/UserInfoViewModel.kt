package com.wenger.natifetask3.ui.fragments.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInfoViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getUserInfo(uuid: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val user = repository.getUserById(uuid)
            _userInfo.value = user
            _isLoading.value = false
        }
    }
}