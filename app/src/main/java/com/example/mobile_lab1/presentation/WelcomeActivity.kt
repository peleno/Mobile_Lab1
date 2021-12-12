package com.example.mobile_lab1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_lab1.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.welcome_container, WelcomeFragment.newInstance())
            .commit()
    }
}
