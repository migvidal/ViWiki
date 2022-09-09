package com.example.viwiki.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.GenericWikiViewModel
import com.example.viwiki.GenericWikiViewModel.ResponseStatus
import com.example.viwiki.WikipediaApiImpl
import kotlinx.coroutines.launch

class PageViewModel : ViewModel(), GenericWikiViewModel {

    private val _status = MutableLiveData<ResponseStatus>()
    override val status: LiveData<ResponseStatus> = _status

    /**
     * Received article from the WikipediaAPI
     */
    private val _pageResponse = MutableLiveData<PageResponse>()
    val pageResponse: LiveData<PageResponse> = _pageResponse

    /**
     * Received article image(s) from the WikipediaAPI
     */
    private val _pageImagesResponse = MutableLiveData<PageImagesResponse>()
    val pageImagesResponse: LiveData<PageImagesResponse> = _pageImagesResponse


    /**
     * Fetches an article by the provided title
     * @param title The title of the article
     */
    fun fetchArticleByTitle(title: String) {
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
                _pageResponse.value = response
            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
            }
        }
        viewModelScope.launch {
            // Image has its own status. Errors are handled by the loader (Coil)
            val imagesResponse = WikipediaApiImpl.wikipediaApiService.getImagesResponse(title)
            _pageImagesResponse.value = imagesResponse
        }
    }


}