package com.example.mobile_lab1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_lab1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}
