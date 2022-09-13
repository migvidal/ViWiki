package com.example.viwiki.domain.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.GenericWikiViewModel
import com.example.viwiki.GenericWikiViewModel.ResponseStatus
import com.example.viwiki.repository.page.PageRepositoryImpl
import kotlinx.coroutines.launch

/**
 * Handles UI state for a Wikipedia page
 */
class PageViewModel(
    private val pageRepositoryImpl: PageRepositoryImpl,
    val title: String
) : ViewModel(), GenericWikiViewModel {

    private val _status = MutableLiveData<ResponseStatus>()
    override val status: LiveData<ResponseStatus> = _status

    /**
     * Received page from the WikipediaAPI
     */
    private val _page = MutableLiveData<Page>()
    val page: LiveData<Page> = _page

    /**
     * Says whether page is saved in the database
     */
    private val _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean> = _isSaved

    /**
     * Get page from the data source
     */
    fun refreshPage() {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                pageRepositoryImpl.apply {
                    _page.value = getPage(title)
                    _isSaved.value = savedLocally.value
                }
                _status.value = ResponseStatus.DONE
            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
                e.printStackTrace()
            }
        }
    }

    /**
     * Save button handler
     */
    fun onSaveBtnClicked() {
        if (_isSaved.value == true) {
            // TODO
            deletePage()
            _isSaved.value = false
            return
        }
        savePage()
    }

    /**
     * Delete the page
     */
    private fun deletePage() {
        viewModelScope.launch {
            _page.value?.let { pageRepositoryImpl.deletePage(it) }
        }
    }

    /**
     * Save the page locally
     */
    private fun savePage() {
        viewModelScope.launch {
            _page.value?.let { pageRepositoryImpl.savePage(it) }
        }
    }

    /**
     * Init block
     */
    init {
        refreshPage()
    }


}