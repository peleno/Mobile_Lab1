package com.example.mobile_lab1.presentation.news_articles_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobile_lab1.R
import com.example.mobile_lab1.domain.NewsArticle

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleTitle: TextView = itemView.findViewById(R.id.article_title)
    private val sourceName: TextView = itemView.findViewById(R.id.source_name)
    private val userImage: ImageView = itemView.findViewById(R.id.article_image)
    fun bind(newsArticle: NewsArticle) {
        articleTitle.text = newsArticle.title
        sourceName.text = newsArticle.sourceName
        Glide.with(itemView)
            .load(newsArticle.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(userImage)
    }
}
