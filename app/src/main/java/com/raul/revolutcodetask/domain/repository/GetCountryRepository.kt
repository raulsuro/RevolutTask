package com.raul.revolutcodetask.domain.repository

import com.raul.revolutcodetask.domain.model.CountryInfo

interface GetCountryRepository {
    suspend fun retrieveCountry(code: String): CountryInfo
}