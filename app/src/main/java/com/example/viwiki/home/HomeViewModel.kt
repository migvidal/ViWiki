package com.example.viwiki.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.R
import com.example.viwiki.WikiMediaApiImpl
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"

    /**
     * Values for the status of the response
     */
    enum class WikiMediaApiStatus { LOADING, ERROR, DONE }

    /**
     * Response from the WikiMediaApi
     */
    private val _featuredArticleResponse = MutableLiveData<FeaturedArticleResponse>()
    val featuredArticleResponse: LiveData<FeaturedArticleResponse> = _featuredArticleResponse

    /**
     * Status of the response
     */
    private val _status = MutableLiveData<WikiMediaApiStatus>()
    val status: LiveData<WikiMediaApiStatus> = _status

    /**
     * String resource for the information message
     */
    private val _infoMessageRes = MutableLiveData<Int>()
    val infoMessageRes: LiveData<Int> = _infoMessageRes

    /**
     * Fetch today's featured article from the API
     */
    fun fetchTodayFeaturedArticle() {
        val calendar = Calendar.getInstance()
        fetchFeaturedArticle(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    /**
     * Fetch the featured article from the API for a given date
     */
    private fun fetchFeaturedArticle(year: Int, month: Int, day: Int) {
        // Format the date for the API
        val yyyy = year.toString()
        val mm = String.format("%02d", month)
        val dd = String.format("%02d", day)

        // Launch API call coroutine while updating status
        viewModelScope.launch {
            _status.value = WikiMediaApiStatus.LOADING
            _infoMessageRes.value = R.string.loading_message
            try {
                val response = WikiMediaApiImpl.wikiMediaApiService.getFeatured(yyyy, mm, dd)
                _featuredArticleResponse.value = response
                _status.value = WikiMediaApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WikiMediaApiStatus.ERROR
                _infoMessageRes.value = R.string.error_message
                e.printStackTrace()
            }

        }
    }
}