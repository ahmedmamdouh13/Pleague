package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Match {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("utcDate")
    @Expose
    var utcDate: String = ""
    @SerializedName("group")
    @Expose
    var group: String = ""
    @SerializedName("score")
    @Expose
    var score: Score = Score()
    @SerializedName("homeTeam")
    @Expose
    var homeTeam: HomeTeam = HomeTeam()
    @SerializedName("awayTeam")
    @Expose
    var awayTeam: AwayTeam = AwayTeam()

}
