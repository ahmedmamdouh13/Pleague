package com.ahmedmamdouh13.theleague.domain

import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface Repository{
    fun getMatches(offset: Int, index: Int, todayInUtc: String): Single<List<DomainModel>>
    fun favoriteFixture(id: Int)
    fun unFavoriteFixture(id: Int)
    fun getFavoriteMatches(): Flowable<List<DomainModel>>
}