package com.example.searchbooksproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.searchbooksproject.R
import com.example.searchbooksproject.data.db.BookSearchDatabase
import com.example.searchbooksproject.data.repository.BookSearchRepositoryImpl
import com.example.searchbooksproject.databinding.ActivityMainBinding
import com.example.searchbooksproject.ui.viewmodel.BookSearchViewModel
import com.example.searchbooksproject.ui.viewmodel.BookSearchViewModelFactory

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var viewModel: BookSearchViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val database = BookSearchDatabase.getInstance(this)
        val repository = BookSearchRepositoryImpl(database)
        val factory = BookSearchViewModelFactory(repository, this)
        viewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]

        setupJetpackNavigation()
    }
    private fun setupJetpackNavigation() {
        val host = supportFragmentManager.findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment
        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_search,R.id.fragment_favorite,R.id.fragment_setting))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}