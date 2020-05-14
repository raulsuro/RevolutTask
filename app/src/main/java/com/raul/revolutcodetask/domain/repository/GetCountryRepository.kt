package com.raul.revolutcodetask.domain.repository

import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.model.Output

interface GetCountryRepository {
    suspend fun retrieveCountry(code: String): Output<CountryInfo>
}