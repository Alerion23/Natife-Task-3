package com.wenger.natifetask3.ui.fragments.info

import androidx.lifecycle.*
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.domain.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInfoViewModel @AssistedInject constructor(
    private val repository: UserRepository,
    @Assisted private val userId: String
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    @AssistedFactory
    interface UserInfoFactory {
        fun create(userId: String): UserInfoViewModel
    }

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

    companion object {
        fun providesFactory(
            assistedFactory: UserInfoFactory,
            userId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId) as T
            }
        }
    }
}