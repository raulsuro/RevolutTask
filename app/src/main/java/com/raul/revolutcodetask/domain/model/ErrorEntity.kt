package com.raul.revolutcodetask.domain.model

sealed class ErrorEntity {

    object Network : ErrorEntity()

    object Cancellation : ErrorEntity()

    object NotFound : ErrorEntity()

    object AccessDenied : ErrorEntity()

    object ServiceUnavailable : ErrorEntity()

    object Unknown : ErrorEntity()
}