package com.example.viwiki

import android.app.Application
import timber.log.Timber

/**
 * Main application class
 */
class ViWikiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}