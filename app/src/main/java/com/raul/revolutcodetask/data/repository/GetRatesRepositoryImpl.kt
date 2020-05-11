package com.raul.revolutcodetask.data.repository

import com.raul.revolutcodetask.data.mapper.NetworkToDomainCurrenciesMapper
import com.raul.revolutcodetask.data.network.api.RatesApi
import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.repository.GetRatesRepository
import javax.inject.Inject

class GetRatesRepositoryImpl @Inject constructor(
    private val ratesApi: RatesApi,
    private val networkToDomainCurrenciesMapper: NetworkToDomainCurrenciesMapper
) : GetRatesRepository {
    override suspend fun retrieveRates(code: String): Currencies {
        return networkToDomainCurrenciesMapper.map(ratesApi.getRates(code).body()!!)
    }
}