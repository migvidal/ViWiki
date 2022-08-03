package com.example.viwiki.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.R
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.network.ApiUtils
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    /**
     * Private mutable live data
     */
    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchResponse: LiveData<SearchResponse> = _searchResponse

    /**
     * Status of the response
     */
    private val _status = MutableLiveData<ApiUtils.ApiStatus>()
    val status: LiveData<ApiUtils.ApiStatus> = _status

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
            _status.value = ApiUtils.ApiStatus.LOADING
            _infoMessageRes.value = R.string.loading_message
            try {
                val response = WikipediaApiImpl.wikipediaApiService.getSearchResults(query)
                _searchResponse.value = response
                _status.value = ApiUtils.ApiStatus.DONE

                // Set message if there are no results
                if (_searchResponse.value?.query?.searchInfo?.totalHits == 0) {
                    _status.value = ApiUtils.ApiStatus.BLANK
                    _infoMessageRes.value = R.string.no_results_message
                }

            } catch (e: Exception) {
                _status.value = ApiUtils.ApiStatus.ERROR
                _infoMessageRes.value = R.string.error_message
                e.printStackTrace()
            }
        }
    }
}