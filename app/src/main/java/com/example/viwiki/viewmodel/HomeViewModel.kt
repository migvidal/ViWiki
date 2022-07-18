package com.example.viwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.WikiMediaApiImpl
import com.example.viwiki.WikiMediaApiService
import com.example.viwiki.model.FeaturedArticleResponse
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {
    val _featuredArticleResponse: MutableLiveData<FeaturedArticleResponse> by lazy {
        MutableLiveData<FeaturedArticleResponse>()
    }
    val featuredArticleResponse: LiveData<FeaturedArticleResponse> = _featuredArticleResponse

    fun fetchTodaysFeaturedArticle() {
        val calendar = Calendar.getInstance()
        fetchFeaturedArticle(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }


    private fun fetchFeaturedArticle(year: Int, month: Int, day: Int) {
        val yyyy = year.toString()
        val mm = String.format("%02d", month)
        val dd = String.format("%02d", day)
        viewModelScope.launch {
            try {
                val response = WikiMediaApiImpl.wikiMediaApiService.getFeatured(yyyy, mm, dd)
                _featuredArticleResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}