package com.wenger.natifetask3.domain

import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.User

interface UserRepository {

    suspend fun getAllUsers(list: (List<User>) -> Unit)

    suspend fun getUserById(uuid: String) : User

}