package com.example.mobile_lab1.data

import com.example.mobile_lab1.data.entity.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

const val BASE_URL = "https://randomuser.me/api/"

interface RandomUserApi {
    @GET("?results=10")
    fun getUsers(): Single<UserResponse>
}
