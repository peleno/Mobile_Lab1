package com.example.mobile_lab1.domain

import com.example.mobile_lab1.data.entity.NewsResponse
import io.reactivex.Single

interface Repository {
    fun getNewsArticles(): Single<NewsResponse>
}
