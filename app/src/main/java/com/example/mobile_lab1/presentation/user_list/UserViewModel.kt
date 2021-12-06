package com.example.mobile_lab1.presentation.user_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_lab1.domain.Repository
import com.example.mobile_lab1.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserViewModel(private val repository: Repository) : ViewModel() {
    val userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()

    private var compositeDisposable = CompositeDisposable()

    fun getUsers() {
        compositeDisposable.add(
            repository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    val userList = mutableListOf<User>()
                    for (user in response.results) {
                        userList.add(User(user.name.first, user.name.last, user.picture.medium))
                    }
                    return@map userList
                }
                .subscribe({ result ->
                    Timber.d("UPDATE user list live data")
                    userList.value = result
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
