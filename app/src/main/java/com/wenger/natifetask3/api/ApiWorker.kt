package com.wenger.natifetask3.api

import com.wenger.natifetask3.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiWorker {

    private var mClient: OkHttpClient? = null

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

    fun provideUserListApi() : ApiService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)
}