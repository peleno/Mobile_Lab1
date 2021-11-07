package com.example.mobile_lab1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

private const val PASSWORDS_MISMATCH_ERROR = "Passwords are not matching"

class SignUpViewModel : ViewModel() {

    val nameError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<String>()

    fun signUp(name: String?, email: String?, password: String?, confirmedPassword: String?): Boolean {
        var isValidName = true
        var isValidEmail = true
        var isValidPassword = true

        try {
            NameValidator(name).validate()
        } catch (e: IllegalStateException) {
            nameError.value = e.message
            isValidName = false
        }
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

        var doPasswordsMatch = false

        if (isValidPassword) {
            doPasswordsMatch = password == confirmedPassword
            if (!doPasswordsMatch) {
                confirmPasswordError.value = PASSWORDS_MISMATCH_ERROR
            }
        }

        return isValidName && isValidEmail && isValidPassword && doPasswordsMatch
    }
}
