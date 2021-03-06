package com.example.mobile_lab1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private var signUpButton: Button? = null

    private var nameTextInputLayout: TextInputLayout? = null
    private var nameTextView: TextInputEditText? = null

    private var emailTextInputLayout: TextInputLayout? = null
    private var emailTextView: TextInputEditText? = null

    private var passwordTextInputLayout: TextInputLayout? = null
    private var passwordTextView: TextInputEditText? = null

    private var confirmPasswordInputLayout: TextInputLayout? = null
    private var confirmPasswordTextView: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initializeToolbar()
        initializeViews()
        signUpButton?.setOnClickListener {
            val isValidationSuccessful = validateInputFields()
            if (isValidationSuccessful) {
                displaySuccess()
            }
        }
    }

    private fun displaySuccess() {
        clearInputFieldsText()
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage("You have successfully signed up!")
        builder.show()
    }

    private fun initializeToolbar() {
        setSupportActionBar(findViewById(R.id.sign_up_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
    }

    private fun validateInputFields(): Boolean {
        clearInputFieldsError()

        val isValidName = InputTextValidator.validateName(nameTextView, nameTextInputLayout)
        val isValidEmail = InputTextValidator.validateEmail(emailTextView, emailTextInputLayout)
        val isValidPassword =
            InputTextValidator.validatePassword(passwordTextView, passwordTextInputLayout)
        var doPasswordsMatch = false

        if (isValidPassword) {
            doPasswordsMatch =
                passwordTextView?.text.toString() == confirmPasswordTextView?.text.toString()
            if (!doPasswordsMatch) {
                confirmPasswordInputLayout?.error = "Passwords are not matching"
            }
        }

        return isValidName && isValidEmail && isValidPassword && doPasswordsMatch
    }

    private fun initializeViews() {
        signUpButton = findViewById(R.id.sign_up_button)

        nameTextInputLayout = findViewById(R.id.name_layout)
        nameTextView = findViewById(R.id.name_input_edit_text)

        emailTextInputLayout = findViewById(R.id.email_layout)
        emailTextView = findViewById(R.id.email_input_edit_text)

        passwordTextInputLayout = findViewById(R.id.password_layout)
        passwordTextView = findViewById(R.id.password_input_edit_text)

        confirmPasswordInputLayout = findViewById(R.id.confirm_password_layout)
        confirmPasswordTextView = findViewById(R.id.confirm_password_input_edit_text)
    }

    private fun clearInputFieldsError() {
        nameTextInputLayout?.error = null
        emailTextInputLayout?.error = null
        passwordTextInputLayout?.error = null
        confirmPasswordInputLayout?.error = null
    }

    private fun clearInputFieldsText() {
        nameTextView?.text?.clear()
        emailTextView?.text?.clear()
        passwordTextView?.text?.clear()
        confirmPasswordTextView?.text?.clear()
    }
}
