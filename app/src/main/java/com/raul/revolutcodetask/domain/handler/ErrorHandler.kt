package com.raul.revolutcodetask.domain.handler

import com.raul.revolutcodetask.domain.model.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}