package com.wenger.natifetask3.domain

import androidx.paging.PagingData
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<PagingData<ResultResponse>>

    suspend fun getUserById(uuid: String): User

}