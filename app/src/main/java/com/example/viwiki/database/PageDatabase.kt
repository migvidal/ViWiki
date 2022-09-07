package com.example.viwiki.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database for storing Pages
 */
@Database(entities = [Page::class], version = 1, exportSchema = false)
abstract class PageDatabase: RoomDatabase() {
    /**
     * Connects DB to the DAO.
     */
    abstract val pageDatabaseDao: PageDatabaseDao

    companion object {
        /**
         * Database instance
         */
        private var INSTANCE: PageDatabase? = null

        /**
         * Get the singleton db instance
         */
        fun getInstance(context: Context): PageDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PageDatabase::class.java,
                    "page_db"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}