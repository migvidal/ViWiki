package com.example.viwiki.domain.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viwiki.domain.page.Page
import com.example.viwiki.repository.page.PageRepositoryImpl
import kotlinx.coroutines.launch
import timber.log.Timber

class SavedViewModel(private val pageRepositoryImpl: PageRepositoryImpl) : ViewModel() {
    private val _savedPages: MutableLiveData<List<Page>> = MutableLiveData(listOf(Page()))
    val savedPages: LiveData<List<Page>> = _savedPages

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            try {
                val x = pageRepositoryImpl.getAllPages()
                _savedPages.value = x
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}