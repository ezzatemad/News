package com.example.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class myApp:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}