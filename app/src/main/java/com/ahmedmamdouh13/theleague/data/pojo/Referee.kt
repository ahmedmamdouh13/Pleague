package com.ahmedmamdouh13.theleague.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Referee {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("nationality")
    @Expose
    var nationality: Any? = null

}
