package com.wenger.natifetask3.domain

import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.data.UserResponse
import com.wenger.natifetask3.data.managers.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(
    private val api: ApiWorker,
    private val dataManager: DataManager
) : UserRepository {

    override suspend fun getAllUsers(list: (List<ResultResponse>) -> Unit) {
        withContext(Dispatchers.IO) {
            api.provideUserListApi().getUsers().enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    val result = response.body()?.results
                    if (result != null) {
                        list(result)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    val result = emptyList<ResultResponse>()
                    list(result)
                }
            })
        }
    }

    override suspend fun getAllDatabaseUsers(): List<User> {
        return dataManager.getAllUsers()
    }

    override suspend fun saveUsers(users: List<User>) {
        return dataManager.uploadUser(users)
    }

    override suspend fun getUserById(uuid: String): User {
        return dataManager.getUserById(uuid)
    }

    override suspend fun deleteAllUsers() {
        dataManager.clearUsers()
    }

}