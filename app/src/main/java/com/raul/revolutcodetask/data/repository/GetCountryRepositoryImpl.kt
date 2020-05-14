package com.raul.revolutcodetask.data.repository

import com.raul.revolutcodetask.data.mapper.NetworkToDomainCountriesMapper
import com.raul.revolutcodetask.data.network.api.CountriesApi
import com.raul.revolutcodetask.data.network.model.NetworkResponse
import com.raul.revolutcodetask.data.network.util.process
import com.raul.revolutcodetask.domain.handler.ErrorHandler
import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.model.Output
import com.raul.revolutcodetask.domain.model.Output.Error
import com.raul.revolutcodetask.domain.model.Output.Success
import com.raul.revolutcodetask.domain.repository.GetCountryRepository
import javax.inject.Inject

class GetCountryRepositoryImpl @Inject constructor(
    private val countriesApi: CountriesApi,
    private val mapper: NetworkToDomainCountriesMapper,
    private val errorHandler: ErrorHandler
) : GetCountryRepository {
    override suspend fun retrieveCountry(code: String): Output<CountryInfo> {
        return try {
            when (val response = countriesApi.getCountry(code).process()) {
                is NetworkResponse.Success -> Success(mapper.map(response.data))
                is NetworkResponse.Error -> Error(errorHandler.getError(response.throwable))
            }
        } catch (e: Exception) {
            Error(errorHandler.getError(e))
        }
    }
}