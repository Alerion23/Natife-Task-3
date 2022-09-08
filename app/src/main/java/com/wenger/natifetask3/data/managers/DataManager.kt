package com.wenger.natifetask3.data.managers

import com.wenger.natifetask3.data.User

interface DataManager {

    suspend fun uploadUser(users: List<User>)

    suspend fun getUserById(uuid: String) : User

    suspend fun clearUsers()

    suspend fun getAllUsers() : List<User>
}