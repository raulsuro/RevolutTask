package com.raul.revolutcodetask.domain.repository

import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.model.Output

interface GetRatesRepository {
    suspend fun retrieveRates(code: String): Output<Currencies>
}
