package com.example.viwiki.repository.page

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viwiki.domain.page.Page

/**
 * Database for storing Pages
 */
@Database(entities = [DatabasePage::class], version = 1, exportSchema = false)
abstract class PageDatabase private constructor(): RoomDatabase() {
    /**
     * Connects DB to the DAO.
     */
    abstract val pageDatabaseDao: PageDatabaseDao

    companion object {
        /**
         * Database instance
         */
        private lateinit var INSTANCE: PageDatabase

        /**
         * Get the singleton db instance
         */
        fun getInstance(context: Context): PageDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PageDatabase::class.java,
                        "page_db").build()
                }
            }
            return INSTANCE
        }
    }
}