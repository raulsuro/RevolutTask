package com.raul.revolutcodetask.data.network.model

import com.google.gson.annotations.SerializedName

class CurrencyInfo(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)
