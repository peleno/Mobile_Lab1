package com.example.mobile_lab1

import android.app.Application
import timber.log.Timber

class MobileLab : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}