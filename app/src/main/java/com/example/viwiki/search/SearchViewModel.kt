package com.example.viwiki.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.WikipediaApiImpl
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    /**
     * Private mutable live data
     */
    private val _searchResponse: MutableLiveData<SearchResponse> by lazy {
        MutableLiveData<SearchResponse>()
    }

    /**
     * Public immutable live data to expose data to other classes
     */
    val searchResponse: LiveData<SearchResponse> = _searchResponse

    /**
     * Fetches the search results by the provided search term
     * @param query The search term
     */
    fun searchArticles(query: String) {
        viewModelScope.launch {
            WikipediaApiImpl.wikipediaApiService.getSearchResults(query)
        }
    }
}