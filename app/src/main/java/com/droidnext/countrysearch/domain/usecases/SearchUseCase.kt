package com.droidnext.countrysearch.domain.usecases

import com.droidnext.countrysearch.domain.model.ApiResponse
import com.droidnext.countrysearch.domain.model.CountryDTO
import com.droidnext.countrysearch.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(val repository: ISearchRepository) {

    fun searchQuery(query: String) : Flow<ApiResponse<List<CountryDTO>>> = flow {
        emit(ApiResponse.Loading)
        repository
            .getSearchResult(query)
            .fold(
                onSuccess = {
                    emit(ApiResponse.Success(it))
                },
                onFailure = {
                    emit(ApiResponse.Error(it.message.orEmpty()))
                }
            )
    }
}