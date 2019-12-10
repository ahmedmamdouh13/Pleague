package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Season {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("startDate")
    @Expose
    var startDate: String? = null
    @SerializedName("endDate")
    @Expose
    var endDate: String? = null
    @SerializedName("currentMatchday")
    @Expose
    var currentMatchday: Int? = null

}
