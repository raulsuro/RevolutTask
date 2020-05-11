package com.raul.revolutcodetask.data.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.raul.revolutcodetask.data.network.model.RatesApiResponse
import java.lang.reflect.Type

class RatesDeserializer : JsonDeserializer<RatesApiResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RatesApiResponse {
        val jsonObject = json.asJsonObject
        val ratesList = ArrayList<HashMap<String, Double>>()

        val base = jsonObject.get("baseCurrency").asString
        val rates = jsonObject.get("rates")
        for (i in 0..rates.asJsonObject.size()) {
            rates.asJsonObject.keySet()
        }

        val entries: Set<Map.Entry<String?, JsonElement?>> =
            (rates as JsonObject).entrySet()

        for ((key, value) in entries) {
            ratesList.add(hashMapOf(key!! to value!!.asDouble))
        }

        return RatesApiResponse(base, ratesList)

    }

}