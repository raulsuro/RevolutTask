package com.raul.revolutcodetask.data.network.model

sealed class NetworkResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class Error(val throwable: Throwable) : NetworkResponse<Nothing>()
}