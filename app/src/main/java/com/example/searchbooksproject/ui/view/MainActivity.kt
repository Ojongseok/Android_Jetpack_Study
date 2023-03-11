package com.example.searchbooksproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.searchbooksproject.R
import com.example.searchbooksproject.data.repository.BookSearchRepositoryImpl
import com.example.searchbooksproject.databinding.ActivityMainBinding
import com.example.searchbooksproject.ui.viewmodel.BookSearchViewModel
import com.example.searchbooksproject.ui.viewmodel.BookSearchViewModelFactory

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var viewModel: BookSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = BookSearchRepositoryImpl()
        val factory = BookSearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory) [BookSearchViewModel::class.java]



        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SearchFragment())
                        .commit()
                    true
                }
                R.id.fragment_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavoriteFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}