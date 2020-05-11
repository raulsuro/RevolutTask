package com.raul.revolutcodetask.domain.usecase

import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.repository.GetCountryRepository
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val repository: GetCountryRepository
) {
    suspend fun execute(code: String): CountryInfo {
        return repository.retrieveCountry(code)
    }
}