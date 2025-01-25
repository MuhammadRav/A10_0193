package com.example.finalproject

import android.app.Application
import com.example.finalproject.dependenciesInjection.AppContainer
import com.example.finalproject.dependenciesInjection.PerkebunanContainer

class PerkebunanApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = PerkebunanContainer()
    }
}