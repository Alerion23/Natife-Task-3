package com.wenger.natifetask3.domain

import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.data.UserResponse
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.utils.toUser
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(
    private val api: ApiWorker,
    private val dataManager: DataManager
) : UserRepository {

    override suspend fun getAllUsers(list: (List<User>) -> Unit) {
        withContext(Dispatchers.IO) {
            api.provideUserListApi().getUsers().enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    val result = response.body()?.results
                    if (result != null) {
                        val newList = arrayListOf<User>()
                        result.forEach {
                            newList.add(it.toUser())
                        }
                        list(newList)
                            dataManager.clearUsers()
                            dataManager.uploadUser(newList)
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