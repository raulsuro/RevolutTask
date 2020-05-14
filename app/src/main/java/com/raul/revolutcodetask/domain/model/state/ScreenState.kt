package com.raul.revolutcodetask.domain.model.state

import com.raul.revolutcodetask.domain.model.ErrorEntity

sealed class ScreenState<out T> {
    class Loading<out T> : ScreenState<T>()
    data class Success<out T>(val data: T) : ScreenState<T>()
    class Error<out T>(val throwable: ErrorEntity) : ScreenState<T>()
}