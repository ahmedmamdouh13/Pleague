package com.ahmedmamdouh13.theleague.ui.model

import com.ahmedmamdouh13.theleague.R

object LottieAnimationsRaw {
    fun getRawFile(days: String) =
        when(days){
         "1"   -> R.raw.number_1
         "2"   -> R.raw.number_2
         "3"   -> R.raw.number_3
         "4"   -> R.raw.number_4
         "5"   -> R.raw.number_5
         "6"   -> R.raw.number_6
         "7"   -> R.raw.number_7
         "8"   -> R.raw.number_8
         "9"   -> R.raw.number_9
         else   -> R.raw.number_0
        }
}