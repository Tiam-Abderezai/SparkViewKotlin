package com.example.currencytrackerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val BASE_URL = "https://revolut.duckdns.org/"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var dailyData: List<Currency>
    private var mCurrencyList: ArrayList<Currency> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val currencyService = retrofit.create(Service::class.java)


        for (i: Int in 1..25) {
            currencyService.getLatestRates("USD").enqueue(object : Callback<Currency> {
                override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                    Log.e(TAG, "onResponse $response")
                    val currencyData = response.body()
                    if (currencyData == null) {
                        Log.w(TAG, "Did not receive a valid response body")
                        return
                    }
                    var currencies: Currency
                    for (i: Int in 1..currencyData.rates?.size!! - 1) {

                        currencies = Currency(
                            currencyData.base,
                            currencyData.date,
                            currencyData.rates

                        )
                        mCurrencyList.add(currencies)
                    }
                    dailyData = mCurrencyList
                    val adapter = SparkAdapter(dailyData)
                    sparkView.adapter = adapter
                }

                override fun onFailure(call: Call<Currency>, t: Throwable) {
                    Log.e(TAG, "onFailure $t")
                }

            })
        }
    }

}
