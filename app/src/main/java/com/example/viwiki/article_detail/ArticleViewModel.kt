package com.example.viwiki.article_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.GenericWikiViewModel
import com.example.viwiki.GenericWikiViewModel.ResponseStatus
import com.example.viwiki.WikipediaApiImpl
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel(), GenericWikiViewModel {

    private val _status = MutableLiveData<ResponseStatus>()
    override val status: LiveData<ResponseStatus> = _status

    /**
     * Received article from the WikipediaAPI
     */
    private val _articleResponse = MutableLiveData<ArticleResponse>()
    val articleResponse: LiveData<ArticleResponse> = _articleResponse

    /**
     * Received article image(s) from the WikipediaAPI
     */
    private val _articleImagesResponse = MutableLiveData<ArticleImagesResponse>()
    val articleImagesResponse: LiveData<ArticleImagesResponse> = _articleImagesResponse



    /**
     * Fetches an article by the provided title
     * @param name The title of the article
     */
    fun fetchArticleByTitle(name: String) {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                val response = WikipediaApiImpl.wikipediaApiService.getArticleResponse(name)
                _articleResponse.value = response
                _status.value = ResponseStatus.DONE
            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
            }
        }
        viewModelScope.launch {
            // Image has its own status. Errors are handled by the loader (Coil)
            val imagesResponse = WikipediaApiImpl.wikipediaApiService.getImagesResponse(name)
            _articleImagesResponse.value = imagesResponse
        }
    }


}