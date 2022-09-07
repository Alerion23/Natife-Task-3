package com.wenger.natifetask3.domain

import com.wenger.natifetask3.api.ApiWorker
import com.wenger.natifetask3.data.UserResponse
import retrofit2.Call

class UserRepositoryImpl : UserRepository {

    override fun getAllUsers(): Call<UserResponse> {
        return ApiWorker.provideUserListApi().getUsers()
    }
}