package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Season {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("startDate")
    @Expose
    var startDate: String = ""
    @SerializedName("endDate")
    @Expose
    var endDate: String = ""
    @SerializedName("currentMatchday")
    @Expose
    var currentMatchday: Int = 0

}
