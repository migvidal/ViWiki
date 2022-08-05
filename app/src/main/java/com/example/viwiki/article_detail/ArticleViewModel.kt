package com.example.viwiki.article_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.WikipediaApiImpl
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    /**
     * The recieved article
     */
    private val _articleResponse = MutableLiveData<ArticleResponse>()
    val articleResponse: LiveData<ArticleResponse> = _articleResponse

    /**
     * The recieved article images
     */
    private val _articleImagesResponse = MutableLiveData<ArticleImagesResponse>()
    val articleImagesResponse: LiveData<ArticleImagesResponse> = _articleImagesResponse

    /**
     * Fetches an article by the provided title
     * @param name The title of the article
     */
    fun fetchArticleByTitle(name: String) {
        viewModelScope.launch {
            try {
                val response = WikipediaApiImpl.wikipediaApiService.getArticleResponse(name)
                _articleResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        viewModelScope.launch {
            try {
                val imagesResponse = WikipediaApiImpl.wikipediaApiService.getImagesResponse(name)
                _articleImagesResponse.value = imagesResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}