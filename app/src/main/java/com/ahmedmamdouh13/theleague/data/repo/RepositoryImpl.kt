package com.ahmedmamdouh13.theleague.data.repo

import com.ahmedmamdouh13.theleague.data.local.MatchEntity
import com.ahmedmamdouh13.theleague.data.local.MatchesDao
import com.ahmedmamdouh13.theleague.data.remote.LeagueService
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import com.ahmedmamdouh13.theleague.domain.Repository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(matchesDao: MatchesDao,leagueService: LeagueService) : Repository {
    private val dao = matchesDao
    private val service = leagueService

    override fun getMatches(offset: Int, index: Int, todayInUtc: String): Single<List<DomainModel>>  {

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
                            group = match.group
                        }
                    }
                    dao.insertMatchesList(matchesList)
                }
        }
        return dao.getMatches(offset, index, todayInUtc)
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
                    list.group,
                    list.isFavorite
                )

        }


    }.subscribeOn(Schedulers.io())


    }

    override fun favoriteFixture(id: Int) {
        dao.favoriteMatch(id)
    }

    override fun unFavoriteFixture(id: Int) {
        dao.unFavoriteMatch(id)
    }

    override fun getFavoriteMatches(): Flowable<List<DomainModel>> =
        dao.getFavoriteMatches() .map {
            it.map { list ->

                DomainModel(
                    list.id,
                    list.awayTeam,
                    list.homeTeam,
                    list.date,
                    "",
                    list.homeScore,
                    list.awayScore,
                    list.group,
                    list.isFavorite
                )

            }

        }
}