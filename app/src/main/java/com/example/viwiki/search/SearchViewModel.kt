package com.example.viwiki.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.home.HomeViewModel
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    /**
     * Values for the status of the response
     */
    enum class WikipediaApiStatus {LOADING, ERROR, DONE}


    /**
     * Private mutable live data
     */
    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchLiveData: LiveData<SearchResponse> = _searchResponse

    /**
     * Status of the response
     */
    private val _status = MutableLiveData<WikipediaApiStatus>()
    val status: LiveData<WikipediaApiStatus> = _status

    /**
     * Error message
     */
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    /**
     * Fetches the search results by the provided search term
     * @param query The search term
     */
    fun searchArticles(query: String) {
        viewModelScope.launch {
            _status.value = WikipediaApiStatus.LOADING
            try {
                val response = WikipediaApiImpl.wikipediaApiService.getSearchResults(query)
                _searchResponse.value = response
                _status.value = WikipediaApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WikipediaApiStatus.ERROR
                e.printStackTrace()
            }
        }
    }
}