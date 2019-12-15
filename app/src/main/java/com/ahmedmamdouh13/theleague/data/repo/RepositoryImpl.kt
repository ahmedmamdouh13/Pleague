package com.ahmedmamdouh13.theleague.data.repo

import com.ahmedmamdouh13.theleague.data.local.MatchEntity
import com.ahmedmamdouh13.theleague.data.local.MatchesDao
import com.ahmedmamdouh13.theleague.data.mapper.EntityMapper
import com.ahmedmamdouh13.theleague.data.pojo.Match
import com.ahmedmamdouh13.theleague.data.remote.LeagueService
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import com.ahmedmamdouh13.theleague.domain.Repository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(matchesDao: MatchesDao,
                                         leagueService: LeagueService,
                                         mapperEntity: EntityMapper
) : Repository {
    private val dao = matchesDao
    private val service = leagueService
    private val mapper = mapperEntity

    override fun getMatches(): Single<List<DomainModel>> {

        return object : Single<List<DomainModel>>() {
            override fun subscribeActual(observer: SingleObserver<in List<DomainModel>>) {

       val d = service.getMatches()
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.matches
                            .map { match ->
                                updateDatabase(match)
                                mapper.mapMatchToDomain(match)
                        }
                    }
            .subscribe { listmapped  , e->
                if (listmapped != null)
                       observer.onSuccess(listmapped)
                else
                    e.printStackTrace()
                    }
            }


        }

    }

    private fun updateDatabase(match: Match) {
        dao.updateFavorites(match.score.fullTime.awayTeam,
            match.score.fullTime.homeTeam,
            match.id)
    }

    override fun favoriteFixture(domainModel: DomainModel) {
        Single.create<Unit> {
            dao.favoriteMatch(mapper.mapDomainToEntity(domainModel))
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun unFavoriteFixture(id: Int) {
        Single.create<Unit> {
            dao.unFavoriteMatch(id)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getFavoriteMatches(): Flowable<List<DomainModel>> =
        dao.getFavoriteMatches().map {
            it.map { list ->
                mapper.mapEntityToDomain(list)
            }
        }

    override fun isMatchFavorite(id: Int): Boolean = dao.getisFavoriteFromMatches(id)

}