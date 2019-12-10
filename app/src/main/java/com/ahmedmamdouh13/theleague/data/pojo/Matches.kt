package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Matches {

    @SerializedName("matches")
    @Expose
    var matches: List<Match> = listOf()

}
