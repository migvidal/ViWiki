package com.example.viwiki.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.GenericWikiViewModel
import com.example.viwiki.GenericWikiViewModel.ResponseStatus
import com.example.viwiki.R
import com.example.viwiki.WikipediaApiImpl
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(), GenericWikiViewModel {

    private val _status = MutableLiveData<ResponseStatus>()
    override val status: LiveData<ResponseStatus> = _status

    /**
     * Response from a search in the WikipediaAPI
     */
    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchResponse: LiveData<SearchResponse> = _searchResponse


    /**
     * String resource for the information message
     */
    private val _infoMessageRes = MutableLiveData<Int>()
    val infoMessageRes: LiveData<Int> = _infoMessageRes

    /**
     * Fetches the search results by the provided search term
     * @param query The search term
     */
    fun searchArticles(query: String) {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                val response = WikipediaApiImpl.wikipediaApiService.getSearchResults(query)
                _searchResponse.value = response

                // Set message if there are no results
                if (_searchResponse.value?.query?.searchInfo?.totalHits == 0) {
                    _status.value = ResponseStatus.BLANK
                } else
                    _status.value = ResponseStatus.DONE

            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
            }
        }
    }
}