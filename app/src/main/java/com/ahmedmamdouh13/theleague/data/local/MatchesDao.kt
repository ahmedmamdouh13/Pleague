package com.ahmedmamdouh13.theleague.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchesList(matchEntity: List<MatchEntity>): List<Long>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(matchEntity: MatchEntity): Long

    @Query("SELECT * FROM `match` WHERE date >= :date ORDER By date LIMIT :pageSize OFFSET :pageIndex ")
    fun getMatches(pageSize: Int, pageIndex: Int, date: String): Single<List<MatchEntity>>

    @Query("SELECT * FROM `match` ORDER By date ")
    fun getFavoriteMatches(): Flowable<List<MatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favoriteMatch(matchEntity: MatchEntity)


    @Query("DELETE FROM `match` WHERE id = :mId")
    fun unFavoriteMatch(mId: Int)

    @Query(" SELECT isFavorite FROM `match` WHERE id = :id")
    fun getisFavoriteFromMatches(id: Int): Boolean

    @Query("UPDATE `match` SET awayScore = :awayScore , homeScore = :homeScore WHERE id = :id")
    fun updateFavorites(awayScore: Int, homeScore: Int, id: Int)

}