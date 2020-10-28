package com.hnativ.androidlabpokedex

import android.app.Application
import com.hnativ.androidlabpokedex.di.AppComponent
import com.hnativ.androidlabpokedex.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }
}