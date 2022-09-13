package com.wenger.natifetask3.ui.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserRepository,
) : ViewModel() {

    private val _userList = SingleLiveEvent<List<User>>()
    val userList: LiveData<List<User>> = _userList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        getUsers()
    }

    fun getUsers() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            repository.getAllUsers {
                val currentList = _userList.value ?: emptyList()
                _userList.value = currentList + it
                _isLoading.value = false

            }
        }
    }
}