package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Score {

    @SerializedName("winner")
    @Expose
    var winner: String = ""
    @SerializedName("duration")
    @Expose
    var duration: String = ""
    @SerializedName("fullTime")
    @Expose
    var fullTime: FullTime = FullTime()
    @SerializedName("halfTime")
    @Expose
    var halfTime: HalfTime = HalfTime()
    @SerializedName("extraTime")
    @Expose
    var extraTime: ExtraTime = ExtraTime()
    @SerializedName("penalties")
    @Expose
    var penalties: Penalties = Penalties()

}
