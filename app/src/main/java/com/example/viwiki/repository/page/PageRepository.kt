package com.example.viwiki.repository.page

import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.WikipediaApiService
import com.example.viwiki.domain.page.Page

/**
 * Source of Wikipedia pages
 */
class PageRepository(
    private val dao: PageDatabaseDao,
    private val api: WikipediaApiImpl
) {

    /**
     * The Wikipedia page
     */
    var page: Page? = null

    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     */
    suspend fun retrievePage(title: String) {
        page = dao.getPageByTitle(title)
        if (page == null) {
            val response = api.wikipediaApiService.getArticleResponse(title)
            page = response.query.pages[0]
        }
    }
}