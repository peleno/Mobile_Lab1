package com.example.mobile_lab1.presentation.news_articles_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_lab1.domain.NewsArticle
import com.example.mobile_lab1.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val repository: Repository) : ViewModel() {
    val articlesList = MutableLiveData<List<NewsArticle>>()
    val errorMessage = MutableLiveData<String>()

    private var compositeDisposable = CompositeDisposable()

    fun getNewsArticles() {
        compositeDisposable.add(
            repository.getNewsArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    val articlesList = mutableListOf<NewsArticle>()
                    for (article in response.articles) {
                        articlesList.add(NewsArticle(article.title, article.source.name, article.image))
                    }
                    return@map articlesList
                }
                .subscribe({ result ->
                    articlesList.value = result
                }, { error ->
                    errorMessage.value = error.message
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("on Cleared")
        compositeDisposable.dispose()
    }
}
