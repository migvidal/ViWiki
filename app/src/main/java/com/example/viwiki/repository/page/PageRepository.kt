package com.example.viwiki.repository.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.domain.page.PageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PageRepository(val dao: PageDatabaseDao, val api: WikipediaApiImpl) {

    private val _pageResponse = MutableLiveData<PageResponse>()
    val pageResponse: LiveData<PageResponse> = _pageResponse

    private suspend fun retrievePageFromDb(pageId: Int) {
        withContext(Dispatchers.IO) {
            dao.getPageById(pageId)
        }
    }

    private suspend fun fetchPageFromNetwork(title: String) {
        api.wikipediaApiService.getArticleResponse(title)
    }
}