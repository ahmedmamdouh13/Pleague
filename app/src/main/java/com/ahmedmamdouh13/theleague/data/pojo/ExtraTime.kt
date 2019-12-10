package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExtraTime {

    @SerializedName("homeTeam")
    @Expose
    var homeTeam: Any? = null
    @SerializedName("awayTeam")
    @Expose
    var awayTeam: Any? = null

}
