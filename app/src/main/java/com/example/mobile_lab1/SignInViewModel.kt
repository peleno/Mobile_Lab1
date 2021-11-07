package com.example.mobile_lab1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

class SignInViewModel : ViewModel() {
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    fun signIn(email: String?, password: String?): Boolean {

        var isValidEmail = true
        var isValidPassword = true

        try {
            EmailValidator(email).validate()
        } catch (e: IllegalStateException) {
            emailError.value = e.message
            isValidEmail = false
        }
        try {
            PasswordValidator(password).validate()
        } catch (e: IllegalStateException) {
            passwordError.value = e.message
            isValidPassword = false
        }

        return isValidPassword && isValidEmail
    }
}