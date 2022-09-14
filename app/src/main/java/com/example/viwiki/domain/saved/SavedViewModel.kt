package com.example.viwiki.domain.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.domain.page.Page
import com.example.viwiki.repository.page.PageRepositoryImpl
import kotlinx.coroutines.launch

class SavedViewModel(private val pageRepositoryImpl: PageRepositoryImpl) : ViewModel() {
    private val _savedPages: MutableLiveData<List<Page>> = MutableLiveData(listOf(Page()))
    val savedPages: LiveData<List<Page>> = _savedPages

    init {
        get()
    }

    private fun get() {
        viewModelScope.launch {
            _savedPages.value = pageRepositoryImpl.getAllPages()
        }
    }

}