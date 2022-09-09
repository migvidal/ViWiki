package com.example.viwiki.domain.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viwiki.repository.page.PageRepository

/**
 * Factory for PageViewModel.
 * @param repository The repository for Page data
 */
class PageViewModelFactory(private val repository: PageRepository) : ViewModelProvider.Factory {
    /**
     * Creates PageViewModel objects.
     * @param modelClass The PageViewModel class
     * @throws IllegalArgumentException if the modelClass is not a PageViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}