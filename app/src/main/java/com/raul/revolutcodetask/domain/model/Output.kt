package com.raul.revolutcodetask.domain.model

sealed class Output<T> {
    data class Success<T>(val data: T) : Output<T>()
    data class Error<T>(val error: ErrorEntity) : Output<T>()
}