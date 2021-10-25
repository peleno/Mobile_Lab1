package com.example.mobile_lab1

import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputTextValidator {

    companion object {
        fun validateName(textView: TextInputEditText?, textInputLayout: TextInputLayout?): Boolean {
            val isEmptyName = textView?.text.isNullOrEmpty()
            if (isEmptyName) {
                textInputLayout?.error = "Name field cannot be blank"
            }
            return !isEmptyName
        }

        fun validateEmail(textView: TextInputEditText?, textInputLayout: TextInputLayout?): Boolean {
            val isEmptyEmail = textView?.text.isNullOrEmpty()
            val isValidEmailFormat = Patterns.EMAIL_ADDRESS.matcher(textView?.text.toString()).matches()
            if (isEmptyEmail) {
                textInputLayout?.error = "Email field cannot be blank"
            } else if (!isValidEmailFormat) {
                textInputLayout?.error = "Incorrect email format"
            }
            return !isEmptyEmail && isValidEmailFormat
        }

        fun validatePassword(textView: TextInputEditText?, textInputLayout: TextInputLayout?): Boolean {
            val isEmptyPassword = textView?.text.isNullOrEmpty()
            val isLongPassword = textView?.text!!.length > 8
            if (isEmptyPassword) {
                textInputLayout?.error = "Password field cannot be blank"
            } else if (!isLongPassword) {
                textInputLayout?.error = "Password should be at least 9 characters long"
            }
            return !isEmptyPassword && isLongPassword
        }
    }
}
