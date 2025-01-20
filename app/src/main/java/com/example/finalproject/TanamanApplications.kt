package com.example.finalproject

import android.app.Application

class TanamanApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= TanamanContainer()
    }
}