package com.ahmedmamdouh13.theleague.data

import com.ahmedmamdouh13.theleague.data.local.MatchesDao
import com.ahmedmamdouh13.theleague.data.remote.LeagueService
import com.ahmedmamdouh13.theleague.domain.Repository
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(matchesDao: MatchesDao,leagueService: LeagueService) : Repository {
    private val dao = matchesDao
    private val service = leagueService

    override fun getMatches() {
     val disp=    service.getTable()
         .subscribeOn(Schedulers.io())
         .subscribe { l , e ->

        }

    }

}