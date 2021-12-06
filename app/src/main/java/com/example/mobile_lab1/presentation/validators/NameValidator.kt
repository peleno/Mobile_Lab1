package com.example.mobile_lab1.presentation.validators

import java.lang.IllegalStateException

private const val EMPTY_NAME_FIELD_ERROR = "Name field cannot be blank"

class NameValidator(field: String?) : InputTextValidator(field) {
    override fun validate() {
        val isEmptyName = this.isEmpty()
        if (isEmptyName) {
            throw IllegalStateException(EMPTY_NAME_FIELD_ERROR)
        }
    }
}