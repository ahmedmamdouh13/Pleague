package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Match {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("season")
    @Expose
    var season: Season? = null
    @SerializedName("utcDate")
    @Expose
    var utcDate: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("matchday")
    @Expose
    var matchday: Int? = null
    @SerializedName("stage")
    @Expose
    var stage: String? = null
    @SerializedName("group")
    @Expose
    var group: String? = null
    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String? = null
    @SerializedName("score")
    @Expose
    var score: Score? = null
    @SerializedName("homeTeam")
    @Expose
    var homeTeam: HomeTeam? = null
    @SerializedName("awayTeam")
    @Expose
    var awayTeam: AwayTeam? = null
    @SerializedName("referees")
    @Expose
    var referees: List<Referee>? = null

}
