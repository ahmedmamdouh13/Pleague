package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Score {

    @SerializedName("winner")
    @Expose
    var winner: String? = null
    @SerializedName("duration")
    @Expose
    var duration: String? = null
    @SerializedName("fullTime")
    @Expose
    var fullTime: FullTime? = null
    @SerializedName("halfTime")
    @Expose
    var halfTime: HalfTime? = null
    @SerializedName("extraTime")
    @Expose
    var extraTime: ExtraTime? = null
    @SerializedName("penalties")
    @Expose
    var penalties: Penalties? = null

}
