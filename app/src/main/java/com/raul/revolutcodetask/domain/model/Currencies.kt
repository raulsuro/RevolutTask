package com.raul.revolutcodetask.domain.model

data class Currencies(
    val baseCurrency: String,
    val rate: List<Rate>
)