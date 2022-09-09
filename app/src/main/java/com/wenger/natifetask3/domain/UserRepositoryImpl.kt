package com.wenger.natifetask3.domain

import com.wenger.natifetask3.api.ApiService
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.data.UserResponse
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.utils.toUser
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(
    private val api: ApiService,
    private val dataManager: DataManager
) : UserRepository {

    private var firstLoad = true

    override suspend fun getAllUsers(list: (List<User>) -> Unit) {
        withContext(Dispatchers.IO) {
            api.getUsers().enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    val result = response.body()?.results
                    if (result != null) {
                        val userList = result.map {
                            it.toUser()
                        }
                        list(userList)
                        if (firstLoad) {
                            firstLoad = false
                            dataManager.clearUsers()
                        }
                        dataManager.uploadUser(userList)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    val result = dataManager.getAllUsers()
                    list(result)
                }
            })
        }
    }

    override suspend fun getUserById(uuid: String): User {
        return dataManager.getUserById(uuid)
    }

}