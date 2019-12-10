package com.ahmedmamdouh13.theleague.domain.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUitl @Inject constructor() {
    private val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val timeZone: TimeZone = SimpleTimeZone.getDefault()


    fun getTimeFromUtcDate(date: String): String{
        val timeFormat = SimpleDateFormat("hh:mm a")
        val utcDate = utcDateFormat.parse(date)
        timeFormat.timeZone = timeZone
        println(date)
        println("dateutil")
        return timeFormat.format(utcDate).toString()
    }

    fun isThisDayToday(date: String): Boolean{
        val matchDate = utcDateFormat.parse(date)
        val todayDate = utcDateFormat.format(Calendar.getInstance().time)

        val match = dateFormat.format(matchDate)

        return todayDate < match
    }

    fun getTodayInUtc(): String {
        return utcDateFormat.format(Calendar.getInstance().time)
    }
}