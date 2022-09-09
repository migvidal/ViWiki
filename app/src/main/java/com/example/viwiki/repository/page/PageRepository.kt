package com.example.viwiki.repository.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.domain.page.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Source of Wikipedia pages
 */
class PageRepository(private val dao: PageDatabaseDao,
                     private val api: WikipediaApiImpl) {

    /**
     * The (private copy) Wikipedia page
     */
    private val _page = MutableLiveData<Page>()
    /**
     * The Wikipedia page
     */
    val page: LiveData<Page> = _page

    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     */
    private suspend fun retrievePage(title: String) {
        withContext(Dispatchers.IO) {
            _page.value = dao.getPageByTitle(title) ?:
            api.wikipediaApiService.getArticleResponse(title).query.pages[0]
        }
    }
}