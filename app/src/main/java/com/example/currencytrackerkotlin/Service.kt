package com.example.currencytrackerkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("latest")
    fun getLatestRates(
        @Query("base") baseRate: String): Call<Currency>
}

