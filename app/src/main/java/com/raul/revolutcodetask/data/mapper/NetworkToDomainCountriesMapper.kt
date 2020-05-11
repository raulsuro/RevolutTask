package com.raul.revolutcodetask.data.mapper

import com.raul.revolutcodetask.data.network.model.CountriesApiResponse
import com.raul.revolutcodetask.domain.model.CountryInfo
import javax.inject.Inject

class NetworkToDomainCountriesMapper @Inject constructor() {
    fun map(origin: List<CountriesApiResponse>): CountryInfo {
        with(origin[0]) {
            return CountryInfo(
                currencies[0].code,
                currencies[0].name,
                flag
            )
        }
    }
}
