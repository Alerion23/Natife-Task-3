package com.wenger.natifetask3.ui.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.domain.UserRepository
import com.wenger.natifetask3.utils.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserListViewModel(
    private val repository: UserRepository,
) : ViewModel() {

    fun getUsers(): Flow<PagingData<User>> {
        return repository.getAllUsers().map {
            it.map { result ->
                result.toUser()
            }
        }.cachedIn(viewModelScope)
    }
}