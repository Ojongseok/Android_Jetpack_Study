package com.example.searchbooksproject.data.api

import androidx.lifecycle.LiveData
import com.example.searchbooksproject.data.model.Book
import com.example.searchbooksproject.data.model.SearchResponse
import com.example.searchbooksproject.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookSearchApi {
    @Headers("Authorization: KakaoAK $API_KEY")
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : Response<SearchResponse>


}