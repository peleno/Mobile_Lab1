package com.example.mobile_lab1.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_lab1.data.RetrofitBuilder
import com.example.mobile_lab1.data.UserRepository

class UserViewModelProviderFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(UserRepository(RetrofitBuilder.api)) as T
    }
}
