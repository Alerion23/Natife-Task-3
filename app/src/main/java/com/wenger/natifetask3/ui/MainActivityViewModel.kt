package com.wenger.natifetask3.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wenger.natifetask3.data.UserResponse
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.utils.SingleLiveEvent
import com.wenger.natifetask3.utils.ViewState

class MainActivityViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _userList = SingleLiveEvent<ViewState<UserResponse>>()
    val userList: LiveData<ViewState<UserResponse>> = _userList

    fun getUsers() {
        val thread = Thread {
            _userList.postValue(ViewState.Loading)
            val response = repository.getAllUsers().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    _userList.postValue(ViewState.Success(it))
                }
            } else {
                Log.d("User list error", "error")
                _userList.postValue(ViewState.Error(response.message()))
            }
        }
        thread.start()
    }

}