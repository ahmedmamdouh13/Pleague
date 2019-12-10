package com.ahmedmamdouh13.theleague.data.remote

import com.ahmedmamdouh13.theleague.data.local.MatchesEntity
import com.ahmedmamdouh13.theleague.data.pojo.Matches
import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers

interface LeagueService {

    @Headers("X-Auth-Token: 930d0ef9d8614490ac411d2ed4a5edf0")
    @GET("/v2/competitions/2021/matches")
    fun getTable(): Single<Matches>

}