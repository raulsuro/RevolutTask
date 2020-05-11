package com.raul.revolutcodetask.domain.model

data class Rate(
    val currency: String,
    val amount: Double,
    val rate: HashMap<String, Double>
)
