package com.example.mobile_lab1.presentation

import android.app.Application
import com.example.mobile_lab1.BuildConfig
import timber.log.Timber

class MobileLab : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}