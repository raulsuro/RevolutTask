package com.raul.revolutcodetask.domain.usecase

import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.repository.GetRatesRepository
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val repository: GetRatesRepository
) {
    suspend fun execute(code: String): Currencies = repository.retrieveRates(code)
}
