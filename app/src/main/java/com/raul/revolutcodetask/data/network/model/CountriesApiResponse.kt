package com.raul.revolutcodetask.data.network.model

data class CountriesApiResponse(
    val currencies: List<CurrencyInfo>,
    val flag: String
)