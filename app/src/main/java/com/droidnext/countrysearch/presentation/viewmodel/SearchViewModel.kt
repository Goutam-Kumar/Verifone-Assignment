package com.droidnext.countrysearch.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidnext.countrysearch.domain.model.CountryDTO
import com.droidnext.countrysearch.domain.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchUseCase: SearchUseCase
): ViewModel() {
    private val _query = MutableStateFlow("")

    private val _searchList = MutableStateFlow(emptyList<CountryDTO>())
    val searchList: StateFlow<List<CountryDTO>> = _searchList

    init {
        viewModelScope.launch {
            _query
                .debounce(300)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collectLatest { query ->
                    performSearch(query)
                }
        }
    }

    fun onQueryChanged(query: String) {
        if (query.isEmpty()) {
            _searchList.value = emptyList<CountryDTO>()
            return
        }
        _query.value = query
    }

    private fun performSearch(query: String) {
        Log.d("Search operation", "Search Operation Performed on query: $query")
        viewModelScope.launch {
            searchUseCase.searchQuery(query)
                .collect { response ->
                    response.fold(
                        onLoading = {},
                        onSuccess = { _searchList.value = it },
                        onError = { message ->
                            Log.d("Network Error", "performSearch: $message")
                        }
                    )
                }
        }
    }
}