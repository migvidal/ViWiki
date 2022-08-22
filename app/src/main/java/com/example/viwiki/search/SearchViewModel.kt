package com.example.viwiki.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.GenericWikiViewModel
import com.example.viwiki.GenericWikiViewModel.ResponseStatus
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.search.SearchResponse.SearchQuery.Search
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
     * Fetches the search results by the provided search term
     * @param query The search term
     */
    fun searchArticles(query: String) {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                val response = WikipediaApiImpl.wikipediaApiService.getSearchResults(query)

                // Set blank status if there is no response
                if (response.query.searchInfo.totalHits == 0) {
                    _status.value = ResponseStatus.BLANK
                } else {
                    // Remove the disambiguations from the results
                    response.query.removeDisambiguationResults()
                    _status.value = ResponseStatus.DONE
                }
                // Save the response
                _searchResponse.value = response

            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
            }
        }
    }



}