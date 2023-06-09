//package com.example.searchbooksproject.ui.viewmodel
//
//import androidx.lifecycle.*
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import androidx.work.Constraints
//import androidx.work.ExistingPeriodicWorkPolicy
//import androidx.work.WorkInfo
//import androidx.work.WorkManager
//import androidx.work.*
//import com.example.searchbooksproject.data.model.Book
//import com.example.searchbooksproject.data.model.SearchResponse
//import com.example.searchbooksproject.data.repository.BookSearchRepository
//import com.example.searchbooksproject.worker.CacheDeleteWorker
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//
//@HiltViewModel
//class BookSearchViewModel @Inject constructor(
//    private val bookSearchRepository: BookSearchRepository,
//    private val workManager: WorkManager,
//    private val savedStateHandle: SavedStateHandle
//) : ViewModel(){
//    // Api
//    private val _searchResult = MutableLiveData<SearchResponse>()
//    val searchResult: LiveData<SearchResponse> get() = _searchResult
//
//    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
//        val response = bookSearchRepository.searchBooks(query, getSortMode(), 1, 15)
//        if (response.isSuccessful) {
//            response.body()?.let {
//                _searchResult.postValue(it)
//            }
//        }
//    }
//
//    // Room
//    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.insertBooks(book)
//    }
//
//    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.deleteBooks(book)
//    }
//
////    val favoriteBooks: Flow<List<Book>> = bookSearchRepository.getFavoriteBooks()
//    val favoriteBooks: StateFlow<List<Book>> = bookSearchRepository.getFavoriteBooks()
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())
//
//    // SavedState
//    var query = String()
//        set(value) {
//            field = value
//            savedStateHandle[SAVE_STATE_KEY] = value
//        }
//    init {
//        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
//    }
//    companion object {
//        private const val SAVE_STATE_KEY = "query"
//        private const val WORKER_KEY = "cache_worker"
//    }
//
//    // DateStore
//    fun saveSortMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.saveSortMode(value)
//    }
//
//    suspend fun getSortMode() = withContext(Dispatchers.IO) {
//        bookSearchRepository.getSortMode().first()
//    }
//
//    fun saveCacheDeleteMode(value: Boolean) = viewModelScope.launch {
//        bookSearchRepository.saveCacheDeleteMode(value)
//    }
//
//    suspend fun getCacheDeleteMode() = withContext(Dispatchers.IO) {
//        bookSearchRepository.getCacheDeleteMode().first()
//    }
//
//    // Paging
//    val favoritePagingBooks: StateFlow<PagingData<Book>> =
//        bookSearchRepository.getFavoritePagingBooks()
//            .cachedIn(viewModelScope)
//            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())
//
//    private val _searchPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
//    val searchPagingResult: StateFlow<PagingData<Book>> = _searchPagingResult.asStateFlow()
//
//    fun searchBooksPaging(query: String) {
//        viewModelScope.launch {
//            bookSearchRepository.searchBooksPaging(query, getSortMode())
//                .cachedIn(viewModelScope)
//                .collect {
//                    _searchPagingResult.value = it
//                }
//        }
//    }
//
//    // WorkManager
//    fun setWork() {
////        val constraints = Constraints.Builder()
////            .setRequiresCharging(true)
////            .setRequiresBatteryNotLow(true)
////            .build()
////        val workRequest = PeriodicWorkRequestBuilder<CacheDeleteWorker>(15, TimeUnit.MINUTES)
////            .setConstraints(constraints)
////            .build()
////
////        workManager.enqueueUniquePeriodicWork(
////            WORKER_KEY, ExistingPeriodicWorkPolicy.REPLACE, workRequest
////        )
//    }
//
//    fun deleteWork() = workManager.cancelUniqueWork(WORKER_KEY)
//
//    fun getWorkStatus(): LiveData<MutableList<WorkInfo>> =
//        workManager.getWorkInfosForUniqueWorkLiveData(WORKER_KEY)
//}