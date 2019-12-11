package com.ahmedmamdouh13.theleague.domain.interactor

import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import io.reactivex.Flowable
import io.reactivex.Single

interface MatchesInteractor {
    fun getMatches(size: Int, index: Int): Single<Map<String, List<DomainModel>>>
    fun getDaysUntilDate(s: String): String
    fun favoriteFixture(id: Int)
    fun unFavoriteFixture(id: Int)
    fun getFavoriteMatches(): Flowable<List<DomainModel>>

}