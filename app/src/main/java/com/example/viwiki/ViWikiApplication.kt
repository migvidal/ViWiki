package com.example.viwiki

import android.app.Application
import com.example.viwiki.repository.page.PageDatabase
import com.example.viwiki.repository.page.PageRepositoryImpl
import timber.log.Timber

/**
 * Main application class
 */
class ViWikiApplication : Application() {
    // DB
    val db by lazy { PageDatabase.getInstance(this).pageDatabaseDao }

    // Repo
    val pageRepositoryImpl by lazy { PageRepositoryImpl(db, WikipediaApiImpl, this) }
    override fun onCreate() {
        super.onCreate()

        // Timber
        Timber.plant(Timber.DebugTree())

    }
}