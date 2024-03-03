package com.example.appoftrainee

import android.app.Application
import com.example.appoftrainee.di.components.DaggerApplicationComponent


class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}