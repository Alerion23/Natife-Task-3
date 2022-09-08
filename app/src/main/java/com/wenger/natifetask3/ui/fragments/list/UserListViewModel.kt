package com.wenger.natifetask3.ui.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenger.natifetask3.data.ResultResponse
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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getUsers()
    }

    private fun getUsers() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            repository.getAllUsers {
                if (it.isNotEmpty()) {
                    val result = getUsersFromResponse(it)
                    viewModelScope.launch(Dispatchers.Main) {
                        repository.deleteAllUsers()
                        repository.saveUsers(result)
                        _userList.value = result
                        _isLoading.value = false
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        val result = repository.getAllDatabaseUsers()
                        if (result != null) {
                            _userList.value = result
                            _isLoading.value = false
                        } else {
                            _error.value = "No users"
                            _isLoading.value = false
                        }
                    }
                }
            }
        }
    }

    private fun getUsersFromResponse(list: List<ResultResponse>): List<User> {
        val userList = arrayListOf<User>()
        list.forEach {
            val uuid = it.id.uuid
            val name = it.name.firstName
            val lastName = it.name.lastName
            val photo = it.picture.url
            val user = User(uuid, name, lastName, photo)
            userList.add(user)
        }
        return userList
    }
}