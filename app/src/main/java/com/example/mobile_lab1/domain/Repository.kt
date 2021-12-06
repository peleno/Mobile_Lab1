package com.example.mobile_lab1.domain

import com.example.mobile_lab1.data.entity.UserResponse
import io.reactivex.Single

interface Repository {
    fun getUsers(): Single<UserResponse>
}
