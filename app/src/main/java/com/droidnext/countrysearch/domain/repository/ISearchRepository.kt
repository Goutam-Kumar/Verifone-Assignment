package com.droidnext.countrysearch.domain.repository

import com.droidnext.countrysearch.domain.model.CountryDTO

interface ISearchRepository {
    suspend fun getSearchResult(query: String): Result<List<CountryDTO>>
}