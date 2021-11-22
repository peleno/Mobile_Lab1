package com.example.mobile_lab1.validators

import android.util.Patterns
import java.lang.IllegalStateException

private const val EMPTY_EMAIL_FIELD_ERROR = "Email field cannot be blank"
private const val EMAIL_FORMAT_ERROR = "Incorrect email format"

class EmailValidator(field: String?) : InputTextValidator(field) {

    override fun validate() {
        val isEmptyEmail = this.isEmpty()
        val isValidEmailFormat = Patterns.EMAIL_ADDRESS.matcher(field as CharSequence).matches()
        if (isEmptyEmail) {
            throw IllegalStateException(EMPTY_EMAIL_FIELD_ERROR)
        } else if (!isValidEmailFormat) {
            throw IllegalStateException(EMAIL_FORMAT_ERROR)
        }
    }
}
