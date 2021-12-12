package com.example.mobile_lab1.data

import com.example.mobile_lab1.data.entity.NewsResponse
import com.example.mobile_lab1.domain.Repository
import io.reactivex.Single

class NewsRepository(private val api: NewsApi) : Repository {
    override fun getNewsArticles(): Single<NewsResponse> {
        return api.getUsers()
    }
}
