package com.example.viwiki.repository.page

import com.example.viwiki.domain.page.Page

/**
 * Interface to the page repository
 */
interface PageRepository {
    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     * @return live data with the Page
     */
    suspend fun getPage(title: String): Page

    suspend fun savePage(page: Page)
}