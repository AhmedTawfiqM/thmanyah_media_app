package com.example.thmanyahmediaapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThmanyahMediaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
