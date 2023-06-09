package com.example.searchbooksproject.ui.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchbooksproject.data.model.Book
import com.example.searchbooksproject.databinding.FragmentSearchBinding
import com.example.searchbooksproject.ui.adapter.BookSearchAdapter
import com.example.searchbooksproject.ui.adapter.BookSearchLoadStateAdapter
import com.example.searchbooksproject.ui.adapter.BookSearchPagingAdapter
import com.example.searchbooksproject.ui.viewmodel.SearchViewModel
import com.example.searchbooksproject.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

//    private lateinit var viewModel: BookSearchViewModel
//    private val viewModel by activityViewModels<BookSearchViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()

    private lateinit var bookSearchAdapter: BookSearchPagingAdapter
//    private lateinit var books : List<Book>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        searchBooks()
        setupLoadState()

//        viewModel.searchResult.observe(viewLifecycleOwner) {
//            books = it.documents
//            bookSearchAdapter.submitList(books)
//        }
        collectLatestStateFlow(searchViewModel.searchPagingResult) {
            bookSearchAdapter.submitData(it)
        }

    }

    private fun setupRecyclerView() {
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
//            adapter = bookSearchAdapter
            adapter = bookSearchAdapter.withLoadStateFooter(
                BookSearchLoadStateAdapter(bookSearchAdapter::retry)
            )
        }

        bookSearchAdapter.setItemClickListener(object : BookSearchPagingAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val action = SearchFragmentDirections.actionFragmentSearchToFragmentBook(bookSearchAdapter.getCurrentList(position))
                findNavController().navigate(action)
            }
        })
    }

    fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime : Long

        binding.etSearch.text = Editable.Factory.getInstance().newEditable(searchViewModel.query)

        binding.etSearch.addTextChangedListener {
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= 100L) {
                it?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
//                        viewModel.searchBooks(query)
                        searchViewModel.searchBooksPaging(query)
                        searchViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    private fun setupLoadState() {
        bookSearchAdapter.addLoadStateListener { combinedLoadStates ->
            val loadState = combinedLoadStates.source
            val isListEmpty = bookSearchAdapter.itemCount < 1
                    && loadState.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached

            binding.tvEmptylist.isVisible = isListEmpty
            binding.rvSearchResult.isVisible = !isListEmpty
            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading

//            binding.btnRetry.isVisible = loadState.refresh is LoadState.Error
//                    || loadState.append is LoadState.Error
//                    || loadState.prepend is LoadState.Error
//            val errorState: LoadState.Error? = loadState.append as? LoadState.Error
//                ?: loadState.prepend as? LoadState.Error
//                ?: loadState.refresh as? LoadState.Error
//            errorState?.let {
//                Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
//            }
        }
//        binding.btnRetry.setOnClickListener {
//            bookSearchAdapter.retry()
//        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}