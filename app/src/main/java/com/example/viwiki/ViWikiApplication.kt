package com.example.viwiki

import android.app.Application
import com.example.viwiki.network.WikipediaApiImpl
import com.example.viwiki.repository.page.PageDatabase
import com.example.viwiki.repository.page.PageRepositoryImpl
import timber.log.Timber

/**
 * Main application class
 */
class ViWikiApplication : Application() {
    /**
     * Database for storing Wikipedia pages
     */
    private val database by lazy { PageDatabase.getInstance(this).pageDatabaseDao }

    /**
     * Single source of truth for Wikipedia pages
     */
    val pageRepositoryImpl by lazy { PageRepositoryImpl(database, this) }
    override fun onCreate() {
        super.onCreate()

        // Timber
        Timber.plant(Timber.DebugTree())

    }
}