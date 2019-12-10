package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Match {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("season")
    @Expose
    var season: Season = Season()
    @SerializedName("utcDate")
    @Expose
    var utcDate: String = ""
    @SerializedName("status")
    @Expose
    var status: String = ""
    @SerializedName("matchday")
    @Expose
    var matchday: Int = 0
    @SerializedName("stage")
    @Expose
    var stage: String = ""
    @SerializedName("group")
    @Expose
    var group: String = ""
    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String = ""
    @SerializedName("score")
    @Expose
    var score: Score = Score()
    @SerializedName("homeTeam")
    @Expose
    var homeTeam: HomeTeam = HomeTeam()
    @SerializedName("awayTeam")
    @Expose
    var awayTeam: AwayTeam = AwayTeam()
    @SerializedName("referees")
    @Expose
    var referees: List<Referee> = listOf()

}
