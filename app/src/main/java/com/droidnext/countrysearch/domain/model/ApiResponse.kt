package com.droidnext.countrysearch.domain.model

sealed class ApiResponse<out T> {
    data object Loading: ApiResponse<Nothing>()
    data class Success<out T> (val data: T): ApiResponse<T>()
    data class Error(val message: String): ApiResponse<Nothing>()

    inline fun <R> fold(
        onLoading: () -> R,
        onSuccess: (T) -> R,
        onError: (String) -> R
    ): R = when (this) {
        is Loading -> onLoading()
        is Success -> onSuccess(data)
        is Error -> onError(message)
    }
}