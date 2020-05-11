package com.raul.revolutcodetask.presentation.model

data class RateView(
    val name: String,
    val flag: String,
    val currency: String,
    var amount: Double,
    var calculatedAmount: Double,
    val rate: HashMap<String, Double>
) {
    fun calculateAmount(multiplier: Double) {
        calculatedAmount = multiplier * amount
    }
}
