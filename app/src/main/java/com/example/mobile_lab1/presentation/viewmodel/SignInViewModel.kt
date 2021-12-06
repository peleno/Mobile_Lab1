package com.example.mobile_lab1.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_lab1.presentation.validators.EmailValidator
import com.example.mobile_lab1.presentation.validators.PasswordValidator
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.lang.IllegalStateException

class SignInViewModel : ViewModel() {
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    private val auth = Firebase.auth
    val user = MutableLiveData<FirebaseUser>()

    fun signIn(email: String?, password: String?) {
        val areSignInFieldsValid = validateSignInFields(email, password)
        if (areSignInFieldsValid) {
            auth.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("signInWithEmail:success")
                        user.value = auth.currentUser
                    } else {
                        Timber.w("signInWithEmail:failure ${task.exception}}")
                        user.value = null
                    }
                }
        } else {
            throw IllegalStateException("invalid input")
        }
    }

    private fun validateSignInFields(email: String?, password: String?): Boolean {
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
