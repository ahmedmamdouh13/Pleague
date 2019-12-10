package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FullTime {

    @SerializedName("homeTeam")
    @Expose
    var homeTeam: Int? = null
    @SerializedName("awayTeam")
    @Expose
    var awayTeam: Int? = null

}
