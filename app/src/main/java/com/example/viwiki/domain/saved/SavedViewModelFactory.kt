package com.example.viwiki.domain.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viwiki.repository.page.PageRepositoryImpl

/**
 * Factory for PageViewModel.
 * @param repository The repository for Page data
 */
class SavedViewModelFactory(
    private val repository: PageRepositoryImpl
) : ViewModelProvider.Factory {
    /**
     * Creates SavedViewModel objects.
     * @param modelClass The SavedViewModel class
     * @throws IllegalArgumentException if the modelClass is not a SavedViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}