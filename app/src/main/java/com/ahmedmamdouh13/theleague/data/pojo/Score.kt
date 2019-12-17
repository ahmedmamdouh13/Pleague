package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Score {
    @SerializedName("fullTime")
    @Expose
    var fullTime: FullTime = FullTime()
}
