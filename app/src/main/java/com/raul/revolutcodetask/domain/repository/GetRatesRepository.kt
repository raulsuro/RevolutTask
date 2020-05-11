package com.raul.revolutcodetask.domain.repository

import com.raul.revolutcodetask.domain.model.Currencies

interface GetRatesRepository {
    suspend fun retrieveRates(code: String): Currencies
}
