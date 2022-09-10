package com.example.viwiki.domain.page

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.GenericWikiViewModel
import com.example.viwiki.GenericWikiViewModel.ResponseStatus
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.repository.page.PageRepository
import kotlinx.coroutines.launch

class PageViewModel(val pageRepository: PageRepository) : ViewModel(), GenericWikiViewModel {

    private val _status = MutableLiveData<ResponseStatus>()
    override val status: LiveData<ResponseStatus> = _status

    /**
     * Received article from the WikipediaAPI
     */
    private val _page = MutableLiveData<Page>()
    val page: LiveData<Page> = _page



    fun fetchArticleByTitle(title: String) {
        viewModelScope.launch {
                _status.value = ResponseStatus.LOADING
                try {
                    pageRepository.retrievePage(title)
                    _page.value = pageRepository.page
                    _status.value = ResponseStatus.DONE
                } catch (e: Exception) {
                    _status.value = ResponseStatus.ERROR
                    e.printStackTrace()
                }
            }
    }

    fun onSaveBtnClicked() {
        savePage()
    }

    private fun savePage() {
        viewModelScope.launch {
            _page.value?.let { pageRepository.savePage(it) }
        }
    }

    /**
     * Fetches an article by the provided title
     * @param title The title of the article
     */
    /*fun fetchArticleByTitleOld(title: String) {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                val response: PageResponse =
                    WikipediaApiImpl.wikipediaApiService.getArticleResponse(title)
                if (response == PageResponse()) {
                    _status.value = ResponseStatus.BLANK
                } else {
                    _status.value = ResponseStatus.DONE
                }
                _page.value = response.
            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
            }
        }
        viewModelScope.launch {
            // Image has its own status. Errors are handled by the loader (Coil)
            val imagesResponse = WikipediaApiImpl.wikipediaApiService.getImagesResponse(title)
            _pageImagesResponse.value = imagesResponse
        }
    }*/


}