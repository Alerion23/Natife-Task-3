package com.wenger.natifetask3.data.managers

import com.wenger.natifetask3.data.User

interface DataManager {

    fun uploadUser(users: List<User>)

    suspend fun getUserById(uuid: String): User

    fun clearUsers()

    fun getAllUsers(): List<User>
}