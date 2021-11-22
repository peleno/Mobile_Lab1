package com.example.mobile_lab1.validators

abstract class InputTextValidator(var field: String?) {

    abstract fun validate()

    fun isEmpty(): Boolean {
        return field.isNullOrEmpty()
    }
}
