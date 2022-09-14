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

    companion object {

        private var mClient: OkHttpClient? = null
        private var mApi: ApiService? = null

        private val client: OkHttpClient
            get() {
                if (mClient == null) {
                    val httpBuilder = OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build()
                    mClient = httpBuilder
                }
                return mClient!!
            }


        fun getInstance(): ApiService {
            if (mApi != null) {
                return mApi!!
            }
            synchronized(this) {
                val apiBuilder = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(ApiService::class.java)
                mApi = apiBuilder
            }
            return mApi!!
        }
    }
}