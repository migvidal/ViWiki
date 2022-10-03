package com.example.viwiki.domain.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viwiki.repository.page.PageRepositoryImpl

/**
 * Factory for PageViewModel.
 * @param repository The repository for DatabasePage data
 */
class PageViewModelFactory(
    private val repository: PageRepositoryImpl,
    private val pageTitle: String
) : ViewModelProvider.Factory {
    /**
     * Creates PageViewModel objects.
     * @param modelClass The PageViewModel class
     * @throws IllegalArgumentException if the modelClass is not a PageViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PageViewModel(repository, pageTitle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}