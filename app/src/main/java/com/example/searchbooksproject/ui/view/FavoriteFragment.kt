package com.example.searchbooksproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchbooksproject.data.model.Book
import com.example.searchbooksproject.databinding.FragmentFavoriteBinding
import com.example.searchbooksproject.ui.adapter.BookSearchAdapter
import com.example.searchbooksproject.ui.adapter.BookSearchPagingAdapter
import com.example.searchbooksproject.ui.viewmodel.BookSearchViewModel
import com.example.searchbooksproject.util.collectLatestStateFlow
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragment: Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter
    private lateinit var books: List<Book>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookSearchViewModel = (activity as MainActivity).viewModel

//        bookSearchViewModel.favoriteBooks.observe(viewLifecycleOwner) {
//            books = it
//            bookSearchAdapter.submitList(books)
//        }
        // LiveData -> Flow로
//        lifecycleScope.launch {
//            bookSearchViewModel.favoriteBooks.collectLatest {
//                bookSearchAdapter.submitList(it)
//            }
//        }
        // Flow -> StateFlow로
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                bookSearchViewModel.favoriteBooks.collectLatest {
//                    bookSearchAdapter.submitList(it)
//                }
//            }
//        }



        // 코루틴 블럭이 너무 길어서서 확장함수로
//        collectLatestStateFlow(bookSearchViewModel.favoriteBooks) {
//            books = it
//            bookSearchAdapter.submitList(it)
//        }
        collectLatestStateFlow(bookSearchViewModel.favoritePagingBooks) {
            bookSearchAdapter.submitData(it)
        }

        setupRecyclerView()

        setupTouchHelper(view)
    }

    private fun setupRecyclerView() {
//        bookSearchAdapter = BookSearchAdapter()
        bookSearchAdapter = BookSearchPagingAdapter()

        binding.rvFavoriteBooks.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = bookSearchAdapter
        }

        bookSearchAdapter.setItemClickListener(object : BookSearchPagingAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val action = FavoriteFragmentDirections.actionFragmentFavoriteToFragmentBook(books[position])
                findNavController().navigate(action)
            }
        })
    }

    private fun setupTouchHelper(view: View) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
//                val book = bookSearchAdapter.currentList[position]
//                bookSearchViewModel.deleteBook(book)
//                Snackbar.make(view, "삭제 완료", Snackbar.LENGTH_SHORT).apply {
//                    setAction("취소") {
//                        bookSearchViewModel.saveBook(book)
//                    }
//                }.show()
                val pagedBook = bookSearchAdapter.peek(position)
                pagedBook?.let { book ->
                    bookSearchViewModel.deleteBook(book)
                    Snackbar.make(view, "삭제됨", Snackbar.LENGTH_SHORT).apply {
                        setAction("Undo") {
                            bookSearchViewModel.saveBook(book)
                        }
                    }.show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavoriteBooks)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}