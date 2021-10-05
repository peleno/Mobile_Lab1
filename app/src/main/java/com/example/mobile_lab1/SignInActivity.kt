package com.example.mobile_lab1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    private var signInButton: Button? = null

    private var emailTextInputLayout: TextInputLayout? = null
    private var emailTextView: TextInputEditText? = null

    private var passwordTextInputLayout: TextInputLayout? = null
    private var passwordTextView: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton = findViewById(R.id.sign_in_button)

        emailTextInputLayout = findViewById(R.id.email_layout)
        emailTextView = findViewById(R.id.email_input_edit_text)

        passwordTextInputLayout = findViewById(R.id.password_layout)
        passwordTextView = findViewById(R.id.password_input_edit_text)

        signInButton?.setOnClickListener {
            emailTextInputLayout?.error = null
            passwordTextInputLayout?.error = null
            val validEmail: Boolean = InputTextValidator.validateEmail(emailTextView, emailTextInputLayout)
            val validPassword: Boolean = InputTextValidator.validatePassword(passwordTextView, passwordTextInputLayout)

            if (validEmail && validPassword) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Success")
                builder.setMessage("You have successfully signed in!")
                builder.show()
            }
        }
    }
}
