package com.wenger.natifetask3.ui.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.utils.SingleLiveEvent

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _userList = SingleLiveEvent<List<ResultResponse>>()
    val userList: LiveData<List<ResultResponse>> = _userList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getUsers()
    }

    private fun getUsers() {
        _isLoading.value = true
        repository.getAllUsers {
            if (it.isNotEmpty()) {
                _userList.value = it
            } else {
                _error.value = "No users"
            }
            _isLoading.value = false
        }
    }

}