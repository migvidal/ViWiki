package com.example.viwiki

import android.app.Application
import com.example.viwiki.repository.page.PageDatabase
import com.example.viwiki.repository.page.PageRepository
import timber.log.Timber

/**
 * Main application class
 */
class ViWikiApplication : Application() {
    // DB
    val db by lazy { PageDatabase.getInstance(this).pageDatabaseDao }
    // Repo
    val pageRepository by lazy { PageRepository(db, WikipediaApiImpl, this) }
    override fun onCreate() {
        super.onCreate()

        // Timber
        Timber.plant(Timber.DebugTree())

    }
}