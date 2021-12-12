package com.example.mobile_lab1.presentation.news_articles_list

import androidx.recyclerview.widget.DiffUtil
import com.example.mobile_lab1.domain.NewsArticle

class ArticleDiffUtilCallback : DiffUtil.ItemCallback<NewsArticle>() {
    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem == newItem
    }
}
