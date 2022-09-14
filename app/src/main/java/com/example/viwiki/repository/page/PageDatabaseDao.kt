package com.example.viwiki.repository.page

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.viwiki.domain.page.Page

/**
 * DAO for the Page database
 */
@Dao
interface PageDatabaseDao {
    @Insert
    suspend fun insertPage(page: Page)

    @Delete
    suspend fun deletePage(page: Page)

    /**
     * Returns a page by the provided name
     */
    @Query("SELECT * FROM ${Page.TABLE_NAME} WHERE title = :title")
    suspend fun getPageByTitle(title: String): Page?

}