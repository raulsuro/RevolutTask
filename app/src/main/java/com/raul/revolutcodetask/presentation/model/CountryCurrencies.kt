package com.raul.revolutcodetask.presentation.model

data class CountryCurrencies(
    val baseCurrency: String,
    val rate: List<RateView>
)