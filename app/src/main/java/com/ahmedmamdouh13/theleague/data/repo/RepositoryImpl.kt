package com.ahmedmamdouh13.theleague.data.repo

import com.ahmedmamdouh13.theleague.data.local.MatchEntity
import com.ahmedmamdouh13.theleague.data.local.MatchesDao
import com.ahmedmamdouh13.theleague.data.remote.LeagueService
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import com.ahmedmamdouh13.theleague.domain.Repository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(matchesDao: MatchesDao,leagueService: LeagueService) : Repository {
    private val dao = matchesDao
    private val service = leagueService

    override fun getMatches(offset: Int, index: Int, todayInUtc: String): Single<List<DomainModel>>  {

        var cnt = 10
        val disposable = service.getMatches()
            .subscribeOn(Schedulers.io())
            .subscribe { list, e ->
//            println("here2 ${list.matches[0].utcDate}")
                if (list != null) {
                    val matchesList = list.matches.map { match ->
                        MatchEntity().apply {
                            id = match.id
                            awayTeam = match.awayTeam.name
                            homeTeam = match.homeTeam.name
                            date = match.utcDate
                            homeScore = match.score.fullTime.homeTeam
                            awayScore = match.score.fullTime.awayTeam
                            group = match.status
                        }
                    }
                    dao.insertMatchesList(matchesList)
                }
        }
        return dao.getMatches(offset, index, todayInUtc)
            .delay(1,TimeUnit.SECONDS)
            .map {
            it.map { list ->

                DomainModel(
                    list.id,
                    list.awayTeam,
                    list.homeTeam,
                    list.date,
                    "",
                    list.homeScore,
                    list.awayScore,
                    list.group
                )

        }


    }.subscribeOn(Schedulers.io())


    }

}