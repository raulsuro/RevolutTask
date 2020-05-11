package com.raul.revolutcodetask.data.network.model

import com.google.gson.annotations.SerializedName

data class RatesApiResponse(
    @SerializedName("baseCurrency")
    val baseCurrency: String?,
    @SerializedName("rates")
    val rates: List<HashMap<String, Double>>?
)
