package com.raul.revolutcodetask.data.network.api

import com.raul.revolutcodetask.data.network.model.RatesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {
    @GET("latest")
    suspend fun getRates(
        @Query("base") code: String
    ): Response<RatesApiResponse>
}