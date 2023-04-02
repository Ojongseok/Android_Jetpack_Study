package com.example.searchbooksproject.ui.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchbooksproject.data.model.Book
import com.example.searchbooksproject.databinding.FragmentSearchBinding
import com.example.searchbooksproject.ui.adapter.BookSearchAdapter
import com.example.searchbooksproject.ui.viewmodel.BookSearchViewModel

class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BookSearchViewModel
    private lateinit var bookSearchAdapter: BookSearchAdapter
    private lateinit var books : List<Book>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        searchBooks()

        viewModel.searchResult.observe(viewLifecycleOwner) {
            books = it.documents
            bookSearchAdapter.submitList(books)
        }


    }

    private fun setupRecyclerView() {
        bookSearchAdapter = BookSearchAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = bookSearchAdapter
        }

        bookSearchAdapter.setItemClickListener(object : BookSearchAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val action = SearchFragmentDirections.actionFragmentSearchToFragmentBook(books[position])
                findNavController().navigate(action)
            }
        })
    }

    fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime : Long

        binding.etSearch.text = Editable.Factory.getInstance().newEditable(viewModel.query)

        binding.etSearch.addTextChangedListener {
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= 100L) {
                it?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
                        viewModel.searchBooks(query)
                        viewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}