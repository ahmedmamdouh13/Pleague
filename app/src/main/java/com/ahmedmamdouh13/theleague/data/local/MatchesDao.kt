package com.ahmedmamdouh13.theleague.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMatchesList(matchEntity: List<MatchEntity>): List<Long>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMatch(matchEntity: MatchEntity): Long

    @Query("SELECT * FROM `match` WHERE date >= :date ORDER By date LIMIT :pageSize OFFSET :pageIndex ")
    fun getMatches(pageSize: Int, pageIndex: Int, date: String): Single<List<MatchEntity>>

    @Query("SELECT * FROM `match` WHERE isFavorite = 1 ")
    fun getFavoriteMatches(): Flowable<List<MatchEntity>>

    @Query("UPDATE `match` SET isFavorite = 1 WHERE id = :mId")
    fun favoriteMatch(mId: Int)

    @Query(" UPDATE `match` SET isFavorite = 0 WHERE id = :mId")
    fun unFavoriteMatch(mId: Int)

}