package com.example.searchbooksproject.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "documents")
    val documents: List<Book>,
    @Json(name = "meta")
    val meta: Meta
)