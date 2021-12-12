package com.example.mobile_lab1.presentation.news_articles_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mobile_lab1.R
import com.example.mobile_lab1.domain.NewsArticle

class ArticleAdapter : ListAdapter<NewsArticle, ArticleViewHolder>(ArticleDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
