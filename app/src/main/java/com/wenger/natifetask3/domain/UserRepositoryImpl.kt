package com.wenger.natifetask3.domain

import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(
    private val api: ApiWorker
) : UserRepository {


    override fun getAllUsers(list: (List<ResultResponse>) -> Unit): (List<ResultResponse>) -> Unit {
        api.provideUserListApi().getUsers().enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
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
        return list
    }
}