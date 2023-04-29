package com.example.searchbooksproject.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import androidx.work.WorkManager
import com.example.searchbooksproject.data.repository.BookSearchRepository
import java.lang.IllegalArgumentException

// viewModel에 stateHandle 추가하기 위해
//class BookSearchViewModelFactory(private val bookSearchRepository: BookSearchRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
//            return BookSearchViewModel(bookSearchRepository) as T
//        }
//        throw IllegalArgumentException("ViewModel class is not found")
//    }
//}
class BookSearchViewModelFactory(
    private val bookSearchRepository: BookSearchRepository,
    private val workManager: WorkManager,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
            return BookSearchViewModel(bookSearchRepository, workManager, handle) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}