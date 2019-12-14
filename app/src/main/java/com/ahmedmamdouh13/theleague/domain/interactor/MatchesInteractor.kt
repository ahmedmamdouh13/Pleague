package com.ahmedmamdouh13.theleague.domain.interactor

import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import io.reactivex.Flowable
import io.reactivex.Single

interface MatchesInteractor {
    fun getMatches(): Single<Map<String, List<DomainModel>>>
    fun getDaysUntilDate(s: String): String
    fun favoriteFixture(domainModel: DomainModel)
    fun unFavoriteFixture(id: Int)
    fun getFavoriteMatches(): Flowable<List<DomainModel>>

}