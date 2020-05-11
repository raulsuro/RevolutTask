package com.raul.revolutcodetask.data.repository

import com.raul.revolutcodetask.data.mapper.NetworkToDomainCountriesMapper
import com.raul.revolutcodetask.data.network.api.CountriesApi
import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.repository.GetCountryRepository
import javax.inject.Inject

class GetCountryRepositoryImpl @Inject constructor(
    private val countriesApi: CountriesApi,
    private val mapper: NetworkToDomainCountriesMapper
) : GetCountryRepository {

    override suspend fun retrieveCountry(code: String): CountryInfo {
        return mapper.map(countriesApi.getCountry(code).body()!!)
    }
}