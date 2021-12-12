package com.example.mobile_lab1.data.entity

data class NewsResponse(
    val articles: List<Article>,
    val totalArticles: Int
)