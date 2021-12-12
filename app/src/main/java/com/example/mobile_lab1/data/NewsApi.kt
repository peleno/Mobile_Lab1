package com.example.mobile_lab1.data

import com.example.mobile_lab1.data.entity.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

const val BASE_URL = "https://gnews.io/api/v4/top-headlines/"

interface NewsApi {
    @GET("?token=02f008661d5ff78f3604aae04a3d7e21&lang=en&max=100")
    fun getUsers(): Single<NewsResponse>
}
