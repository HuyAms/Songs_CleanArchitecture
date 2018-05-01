package com.example.huytrinh.songs.presentation

import android.app.Application
import com.example.huytrinh.songs.presentation.di.Injector

class SongsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.initialize(this)
    }
}