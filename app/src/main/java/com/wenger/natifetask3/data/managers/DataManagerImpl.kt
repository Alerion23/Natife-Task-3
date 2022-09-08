package com.wenger.natifetask3.data.managers

import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.data.UsersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataManagerImpl(private val database: UsersDatabase) : DataManager {

    override fun uploadUser(users: List<User>) {
            database.usersDao().uploadUsers(users)
    }

    override suspend fun getUserById(uuid: String): User {
        return withContext(Dispatchers.IO) {
            database.usersDao().getUserById(uuid)
        }
    }

    override fun clearUsers() {
            database.clearAllTables()
    }

    override fun getAllUsers(): List<User> {
        return database.usersDao().getAll()
    }
}