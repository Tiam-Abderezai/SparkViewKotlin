package com.example.currencytrackerkotlin

import com.robinhood.spark.SparkAdapter

class SparkAdapter(private val dailyData: List<Currency>) : SparkAdapter() {
    override fun getCount() = dailyData.size

    override fun getItem(index: Int) = dailyData[index]

    override fun getY(index: Int): Float {
        var chosenDayData = 0.0F
        if (dailyData[index].rates?.keys!!.contains("EUR")) {
            chosenDayData = dailyData[index].rates?.getValue("EUR")?.toFloat()!!
        }
        return chosenDayData!!
    }

}