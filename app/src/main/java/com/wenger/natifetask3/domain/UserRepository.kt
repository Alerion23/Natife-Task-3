package com.wenger.natifetask3.domain

import com.wenger.natifetask3.data.UserResponse
import retrofit2.Call

interface UserRepository {

    fun getAllUsers() : Call<UserResponse>
}