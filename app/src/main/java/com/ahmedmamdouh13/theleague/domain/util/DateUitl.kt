package com.ahmedmamdouh13.theleague.domain.util

import com.ahmedmamdouh13.theleague.ui.Constants
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.round

class DateUitl @Inject constructor() {




    fun getTimeFromUtcDate(date: String): String{
      return  try {
          val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
          val dateFormat = SimpleDateFormat("yyyy-MM-dd")

          val timeFormat = SimpleDateFormat("hh:mm a")
          utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val utcDate = utcDateFormat.parse(date)
          timeFormat.timeZone = TimeZone.getDefault()


            println(date)

          val toString = timeFormat.format(utcDate!!).toString()

          toString
      }
        catch (e: Exception){
            e.printStackTrace()
           Constants.notAvailable
        }
    }

    fun isThisDayToday(date: String): Boolean{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val matchDate = dateFormat.parse(date)
        val todayDate = dateFormat.format(Calendar.getInstance().time)

        val match = dateFormat.format(matchDate!!)

        return todayDate == match
    }

    fun getTodayInUtc(): String {
        val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")


        val s = utcDateFormat.format(Calendar.getInstance().time)

        return s
    }

    fun getDateFromUtcDate(date: String): String {
        val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val utcDate = utcDateFormat.parse(date)
        dateFormat.timeZone = TimeZone.getDefault()

        return dateFormat.format(utcDate)
    }
    fun daysUntilDate(s: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val todayDate = Calendar.getInstance().time
        val matchDate = dateFormat.parse(s)

         val daysMillis = matchDate!!.time - todayDate.time

        val secMilli = 1000L
        val minMilli = secMilli * 60
        val hourMilli = minMilli * 60
        val daysMilli =  hourMilli * 24

        return "${(daysMillis / daysMilli) + 1}"
    }


}