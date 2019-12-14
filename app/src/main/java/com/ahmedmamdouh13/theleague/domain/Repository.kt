package com.ahmedmamdouh13.theleague.domain

import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository{
    fun getMatches(): Single<List<DomainModel>>
    fun unFavoriteFixture(id: Int)
    fun getFavoriteMatches(): Flowable<List<DomainModel>>
    fun isMatchFavorite(id: Int): Boolean
    fun favoriteFixture(domainModel: DomainModel)
}