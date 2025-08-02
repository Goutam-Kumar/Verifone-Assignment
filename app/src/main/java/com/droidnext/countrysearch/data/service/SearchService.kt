package com.droidnext.countrysearch.data.service

import com.droidnext.countrysearch.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("locations/countries")
    suspend fun getUser(@Query("search") query: String): Response<SearchResponse>
}