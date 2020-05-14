package com.raul.revolutcodetask.data.repository

import com.raul.revolutcodetask.data.mapper.NetworkToDomainCurrenciesMapper
import com.raul.revolutcodetask.data.network.api.RatesApi
import com.raul.revolutcodetask.data.network.model.NetworkResponse
import com.raul.revolutcodetask.data.network.util.process
import com.raul.revolutcodetask.domain.handler.ErrorHandler
import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.model.Output
import com.raul.revolutcodetask.domain.repository.GetRatesRepository
import javax.inject.Inject

class GetRatesRepositoryImpl @Inject constructor(
    private val ratesApi: RatesApi,
    private val networkToDomainCurrenciesMapper: NetworkToDomainCurrenciesMapper,
    private val errorHandler: ErrorHandler
) : GetRatesRepository {
    override suspend fun retrieveRates(code: String): Output<Currencies> {
        return try {
            when (val response = ratesApi.getRates(code).process()) {
                is NetworkResponse.Success -> Output.Success(networkToDomainCurrenciesMapper.map(response.data))
                is NetworkResponse.Error -> Output.Error(errorHandler.getError(response.throwable))
            }
        } catch (e: Exception) {
            Output.Error(errorHandler.getError(e))
        }
    }
}