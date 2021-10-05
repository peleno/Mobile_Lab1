package com.example.mobile_lab1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var signInButton: Button? = null
    private var signUpButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setSupportActionBar(findViewById(R.id.my_toolbar))
        signInButton = findViewById(R.id.sign_in_button)
        signUpButton = findViewById(R.id.sign_up_button)

        signInButton?.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        signUpButton?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
