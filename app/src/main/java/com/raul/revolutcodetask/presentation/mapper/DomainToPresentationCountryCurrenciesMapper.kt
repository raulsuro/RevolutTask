package com.raul.revolutcodetask.presentation.mapper

import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.presentation.model.CountryCurrencies
import com.raul.revolutcodetask.presentation.model.RateView
import javax.inject.Inject

class DomainToPresentationCountryCurrenciesMapper @Inject constructor() {
    fun map(originCurrencies: Currencies, originCountries: List<CountryInfo>): CountryCurrencies {
        val countriesByCurrency: Map<String, CountryInfo> =
            originCountries.associateBy { it.currency }

        val rateView = originCurrencies.rate.filter { countriesByCurrency[it.currency] != null }
            .map { rateInfo ->
                countriesByCurrency[rateInfo.currency].let { country ->
                    RateView(
                        country!!.name,
                        country.flag,
                        rateInfo.currency,
                        rateInfo.amount,
                        1.0,
                        rateInfo.rate
                    )
                }
            }

        return CountryCurrencies(
            originCurrencies.baseCurrency,
            rateView
        )

    }
}