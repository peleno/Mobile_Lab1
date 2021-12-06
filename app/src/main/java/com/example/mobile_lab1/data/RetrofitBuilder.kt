package com.example.mobile_lab1.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val api: RandomUserApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient())
            .build()

        api = retrofit.create(RandomUserApi::class.java)
    }

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

        return OkHttpClient.Builder().addInterceptor(logging).build()
    }
}
