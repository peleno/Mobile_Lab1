package com.example.mobile_lab1

import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputTextValidator {

    companion object {
        fun validateName(textView: TextInputEditText?, textInputLayout: TextInputLayout?): Boolean {
            val isValidName = !textView?.text.isNullOrEmpty()
            if (!isValidName) {
                textInputLayout?.error = "Name field cannot be blank"
            }
            return isValidName
        }

        fun validateEmail(textView: TextInputEditText?, textInputLayout: TextInputLayout?): Boolean {
            val isValidEmail = !textView?.text.isNullOrEmpty()
            if (!isValidEmail) {
                textInputLayout?.error = "Email field cannot be blank"
            }
            return isValidEmail
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

        fun isValidEmail(textView: TextView?): Boolean {
            return !textView?.text.isNullOrEmpty()
        }

        fun isValidPassword(textView: TextView?): Boolean {
            return !textView?.text.isNullOrEmpty() && textView?.text!!.length > 8
        }

        fun isValidName(textView: TextView?): Boolean {
            return !textView?.text.isNullOrEmpty()
        }
    }
}
