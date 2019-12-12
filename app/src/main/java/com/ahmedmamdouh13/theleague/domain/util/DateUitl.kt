package com.ahmedmamdouh13.theleague.domain.util

import com.ahmedmamdouh13.theleague.ui.Constants
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUitl @Inject constructor() {
    private val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    private val mTimeZone: TimeZone = SimpleTimeZone.getDefault()


    fun getTimeFromUtcDate(date: String): String{
      return  try {

            val timeFormat = SimpleDateFormat("hh:mm a")
            val utcDate = utcDateFormat.parse(date)
            println(date)
            println("dateutil")
          timeFormat.format(utcDate).toString()
        }
        catch (e: Exception){
            e.printStackTrace()
           Constants.notAvailable
        }
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
            = dateFormat.format(utcDateFormat.parse(date))

    fun daysUntilDate(s: String): String {
        val todayDate = Calendar.getInstance().time
        val matchDate = dateFormat.parse(s)

         val daysMillis = matchDate.time - todayDate.time

        val secMilli = 1000L
        val minMilli = secMilli * 60
        val hourMilli = minMilli * 60
        val daysMilli =  hourMilli * 24

        return "${daysMillis / daysMilli}"
    }


}