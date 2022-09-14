package com.wenger.natifetask3.api

import com.wenger.natifetask3.data.UserResponse
import com.wenger.natifetask3.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("1.4/?results=20")
    fun getUsers(): Call<UserResponse>
}