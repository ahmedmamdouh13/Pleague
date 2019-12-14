package com.ahmedmamdouh13.theleague.domain.util

import android.annotation.SuppressLint
import com.ahmedmamdouh13.theleague.ui.Constants
import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.round

class DateUitl @Inject constructor() {

    @SuppressLint("SimpleDateFormat")
    fun getTimeFromUtcDate(date: String): String{
      return  try {
          val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

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

    @SuppressLint("SimpleDateFormat")
    fun isThisDayToday(date: String): Boolean{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val matchDate = dateFormat.parse(date)
        val todayDate = dateFormat.format(Calendar.getInstance().time)

        val match = dateFormat.format(matchDate!!)

        return todayDate == match
    }

    @SuppressLint("SimpleDateFormat")
    fun getTodayInUtc(): String {
        val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")


        val s = utcDateFormat.format(Calendar.getInstance().time)

        return s
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateFromUtcDate(date: String): String {
        val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val utcDate = utcDateFormat.parse(date)
        dateFormat.timeZone = TimeZone.getDefault()

        return dateFormat.format(utcDate)
    }
    @SuppressLint("SimpleDateFormat")
    fun daysUntilDate(s: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val todayDate = Calendar.getInstance().time
        val matchDate = dateFormat.parse(s)

         val daysMillis = matchDate!!.time - todayDate.time

        val secMilli = 1000L
        val minMilli = secMilli * 60
        val hourMilli = minMilli * 60
        val daysMilli =  hourMilli * 24

        val s1 = (daysMillis / daysMilli) + 1
        return when {
            s1  < 10 -> "0$s1"
            else -> "$s1"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun isDateValid(date: String): Boolean {
     return  try {
         val utcDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
         val matchDate = utcDateFormat.parse(date)
         val today = utcDateFormat.format(Calendar.getInstance().time)
         val todayDate = utcDateFormat.parse(today)
         todayDate.before(matchDate) || isThisDayToday(date)
     }catch (e: ParseException){
         val dateFormat = SimpleDateFormat("yyyy-MM-dd")
         val matchDate = dateFormat.parse(date)
         val today = dateFormat.format(Calendar.getInstance().time)
         val todayDate = dateFormat.parse(today)
          todayDate.before(matchDate) || isThisDayToday(date)
        }
    }
}