package com.example.searchbooksproject.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.searchbooksproject.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book : Book)

    @Delete
    suspend fun deleteBook(book : Book)
    
//    @Query("SELECT * FROM books")
//    fun getFavoriteBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM books")
    fun getFavoriteBooks() : Flow<List<Book>>

    @Query("SELECT * FROM books")
    fun getFavoritePagingBooks() : PagingSource<Int, Book>
}