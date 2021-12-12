package com.example.mobile_lab1.presentation.news_articles_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_lab1.data.NewsRepository
import com.example.mobile_lab1.data.RetrofitBuilder

class ArticleViewModelProviderFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(NewsRepository(RetrofitBuilder.api)) as T
    }
}
