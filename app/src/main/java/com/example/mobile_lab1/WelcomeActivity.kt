package com.example.mobile_lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
