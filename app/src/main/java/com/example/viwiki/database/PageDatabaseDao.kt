package com.example.viwiki.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * DAO for the Page database
 */
@Dao
interface PageDatabaseDao {
    @Insert
    fun insertPage(page: Page)

    @Delete
    fun deletePage(page: Page)

    /**
     * Returns a page by the provided id
     */
    @Query("SELECT * FROM ${Page.TABLE_NAME} WHERE page_id = :pageId")
    fun getPageById(pageId: Int)

}