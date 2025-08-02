package com.droidnext.countrysearch.data.repository

import com.droidnext.countrysearch.data.model.toCountryDTO
import com.droidnext.countrysearch.data.service.SearchService
import com.droidnext.countrysearch.domain.model.CountryDTO
import com.droidnext.countrysearch.domain.repository.ISearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val service: SearchService,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
): ISearchRepository {

    override suspend fun getSearchResult(query: String): Result<List<CountryDTO>> {
        return withContext(dispatchers) {
            service
                .getUser(query)
                .toResult()
                .map { it.countries.map { country -> country.toCountryDTO() } }
        }
    }

    fun <T> Response<T>.toResult(): Result<T> {
        return if (isSuccessful) {
            val body = body()
            if (body != null) {
                Result.success(body)
            } else {
                Result.failure(NullPointerException("Response body is null"))
            }
        } else {
            Result.failure(HttpException(this))
        }
    }
}