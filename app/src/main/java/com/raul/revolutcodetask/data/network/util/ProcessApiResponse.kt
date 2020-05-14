package com.raul.revolutcodetask.data.network.util

import com.raul.revolutcodetask.data.network.model.NetworkResponse
import retrofit2.Response
import java.io.IOException

fun <T : Any> Response<T>.process(): NetworkResponse<T> {
    return if (this.isSuccessful)
        NetworkResponse.Success(this.body()!!)
    else
        NetworkResponse.Error(IOException())
}