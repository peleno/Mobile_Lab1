package com.example.mobile_lab1.data

import com.example.mobile_lab1.data.entity.UserResponse
import com.example.mobile_lab1.domain.Repository
import io.reactivex.Single

class UserRepository(val api: RandomUserApi) : Repository {
    override fun getUsers(): Single<UserResponse> {
        return api.getUsers()
    }
}
