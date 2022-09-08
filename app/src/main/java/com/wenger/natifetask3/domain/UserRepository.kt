package com.wenger.natifetask3.domain

import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.User

interface UserRepository {

    suspend fun getAllUsers(list: (List<ResultResponse>) -> Unit)

    suspend fun getAllDatabaseUsers() : List<User>

    suspend fun saveUsers(users: List<User>)

    suspend fun getUserById(uuid: String) : User

    suspend fun deleteAllUsers()
}