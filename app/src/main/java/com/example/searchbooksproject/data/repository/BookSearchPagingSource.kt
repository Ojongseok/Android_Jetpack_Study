package com.example.searchbooksproject.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.searchbooksproject.data.api.BookSearchApi
import com.example.searchbooksproject.data.model.Book
import retrofit2.HttpException
import java.io.IOException

class BookSearchPagingSource(
    private val api: BookSearchApi,
    private val query: String,
    private val sort: String
) : PagingSource<Int, Book>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX

            val response = api.searchBooks(query, sort, pageNumber, params.loadSize)
            val endOfPaginationReached = response.body()?.meta?.isEnd!!

            val data = response.body()?.documents!!
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (endOfPaginationReached) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                pageNumber + (params.loadSize / 10)
            }
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}