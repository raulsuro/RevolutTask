package com.raul.revolutcodetask.data.network.model

import com.google.gson.annotations.SerializedName

data class CountriesApiResponse(
    @SerializedName("currencies")
    val currencies: List<CurrencyInfo>,
    @SerializedName("flag")
    val flag: String
)