package com.wenger.natifetask3.api

import com.wenger.natifetask3.data.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("1.4/?results=20")
    fun getUsers(): Call<UserResponse>
}