package com.example.currencytrackerkotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Currency(
    @SerializedName("base")
    @Expose
    val base: String? = null,

    @SerializedName("date")
    @Expose
    val date: String? = null,

    @SerializedName("rates")
    @Expose
    val rates: Map<String, Float>? = null
)
Demonstrates the use of SparkView chart from Robinhood. Pulls currency data USD>EUR 25 times in current time and displays it on the graph