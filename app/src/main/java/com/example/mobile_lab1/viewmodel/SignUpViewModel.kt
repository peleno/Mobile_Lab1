package com.example.mobile_lab1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_lab1.validators.EmailValidator
import com.example.mobile_lab1.validators.NameValidator
import com.example.mobile_lab1.validators.PasswordValidator
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.lang.IllegalStateException

private const val PASSWORDS_MISMATCH_ERROR = "Passwords are not matching"

class SignUpViewModel : ViewModel() {

    val nameError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<String>()
    val user = MutableLiveData<FirebaseUser>()
    private val auth = Firebase.auth

    fun signUp(name: String?, email: String?, password: String?, confirmedPassword: String?) {
        val isSignUpFieldsValid = validateSignUpFields(name, email, password, confirmedPassword)
        if (isSignUpFieldsValid) {
            auth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("createUserWithEmail:success")
                        user.value = auth.currentUser
                    } else {
                        Timber.w("createUserWithEmail:failure ${task.exception}")
                        // user.value = null
                        throw IllegalStateException("Authentication failed.")
                    }
                }
        } else {
            throw IllegalStateException("invalid input")
        }
    }

    private fun validateSignUpFields(
        name: String?,
        email: String?,
        password: String?,
        confirmedPassword: String?
    ): Boolean {
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
