package com.example.searchbooksproject.di

import com.example.searchbooksproject.data.repository.BookSearchRepository
import com.example.searchbooksproject.data.repository.BookSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepository(
        bookSearchRepositoryImpl: BookSearchRepositoryImpl
    ): BookSearchRepository
}