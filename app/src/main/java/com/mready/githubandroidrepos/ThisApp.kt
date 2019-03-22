package com.mready.githubandroidrepos

import android.app.Application
import timber.log.Timber

class ThisApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}