package com.wenger.natifetask3.data.managers

import com.wenger.natifetask3.data.DatabaseWorker
import com.wenger.natifetask3.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataManagerImpl(private val database: DatabaseWorker) : DataManager {

    override suspend fun uploadUser(users: List<User>) {
        withContext(Dispatchers.IO) {
            database.getDatabaseClient().UsersDao().uploadUsers(users)
        }
    }

    override suspend fun getUserById(uuid: String): User {
        return withContext(Dispatchers.IO) {
            database.getDatabaseClient().UsersDao().getUserById(uuid)
        }
    }

    override suspend fun clearUsers() {
        withContext(Dispatchers.IO) {
            database.getDatabaseClient().clearAllTables()
        }
    }

    override suspend fun getAllUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            database.getDatabaseClient().UsersDao().getAll()
        }
    }
}