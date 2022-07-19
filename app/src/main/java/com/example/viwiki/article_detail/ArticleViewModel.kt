package com.example.viwiki.article_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.WikipediaApiImpl
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    /**
     * Private mutable live data
     */
    private val _articleResponse: MutableLiveData<ArticleResponse> by lazy {
        MutableLiveData<ArticleResponse>()
    }

    /**
     * Public immutable live data to expose data to other classes
     */
    val articleResponse: LiveData<ArticleResponse> = _articleResponse

    /**
     * Fetches an article by the provided title
     * @param name The title of the article
     */
    fun fetchArticleByTitle(name: String) {
        viewModelScope.launch {
            val response = WikipediaApiImpl.wikipediaApiService.getArticleResponse(name)
            _articleResponse.value = response
        }
    }
}