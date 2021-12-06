package com.example.mobile_lab1.presentation.validators

import java.lang.IllegalStateException

private const val MIN_PASSWORD_LENGTH = 8
private const val EMPTY_PASSWORD_FIELD_ERROR = "Password field cannot be blank"
private const val MIN_PASSWORD_LENGTH_ERROR = "Password should be at least $MIN_PASSWORD_LENGTH characters long"

class PasswordValidator(field: String?) : InputTextValidator(field) {
    override fun validate() {
        val isEmptyPassword = this.isEmpty()
        val isLongPassword = field!!.length >= MIN_PASSWORD_LENGTH
        if (isEmptyPassword) {
            throw IllegalStateException(EMPTY_PASSWORD_FIELD_ERROR)
        } else if (!isLongPassword) {
            throw IllegalStateException(MIN_PASSWORD_LENGTH_ERROR)
        }
    }
}