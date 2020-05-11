package com.raul.revolutcodetask.data.mapper

import com.raul.revolutcodetask.data.network.model.RatesApiResponse
import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.model.Rate
import javax.inject.Inject

class NetworkToDomainCurrenciesMapper @Inject constructor() {
    fun map(origin: RatesApiResponse): Currencies {
        with(origin) {
            val rates = rates?.map { entry ->
                Rate(
                    entry.iterator().next().key,
                    entry.iterator().next().value,
                    hashMapOf(entry.iterator().next().key to entry.iterator().next().value)
                )
            }
            (rates as ArrayList).add(
                0,
                Rate(baseCurrency ?: "", 1.0, hashMapOf((baseCurrency ?: "") to 1.0))
            )

            return Currencies(
                baseCurrency ?: "",
                rates ?: ArrayList()
            )
        }
    }
}
