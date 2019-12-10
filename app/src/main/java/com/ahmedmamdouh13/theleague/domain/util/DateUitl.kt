package com.ahmedmamdouh13.theleague.domain.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUitl @Inject constructor() {
    private val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val mTimeZone: TimeZone = SimpleTimeZone.getDefault()


    fun getTimeFromUtcDate(date: String): String{
        val timeFormat = SimpleDateFormat("hh:mm a")
        val utcDate = utcDateFormat.parse(date)
        timeFormat.timeZone = mTimeZone
        println(date)
        println("dateutil")
        return timeFormat.format(utcDate).toString()
    }

    fun isThisDayToday(date: String): Boolean{
        val matchDate = dateFormat.parse(date)
        val todayDate = dateFormat.format(Calendar.getInstance().time)

        val match = dateFormat.format(matchDate)

        return todayDate == match
    }

    fun getTodayInUtc(): String {
        return utcDateFormat.format(Calendar.getInstance().time)
    }

    fun getDateFromUtcDate(date: String): String
            = dateFormat.format(utcDateFormat
        .apply { timeZone = mTimeZone }
        .parse(date))

    fun daysUntilDate(s: String): String {
        val todayDate = Calendar.getInstance()
        val matchDate = dateFormat.parse(s)

        var instance = Calendar.getInstance()
        instance.time = matchDate
         val daysMillis = instance.timeInMillis - todayDate.timeInMillis
        instance.timeInMillis = daysMillis

        val secMilli = 1000
        val minMilli = secMilli * 60
        val hourMilli = minMilli * 60
        val daysMilli =  hourMilli * 24

        return "${daysMillis / daysMilli}"
    }

}