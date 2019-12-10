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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchesList(matchEntity: List<MatchEntity>): List<Long>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(matchEntity: MatchEntity): Long

    @Query("SELECT * FROM `match` WHERE date >= :date ORDER By date LIMIT :pageSize OFFSET :pageIndex ")
    fun getMatches(pageSize: Int, pageIndex: Int, date: String): Single<List<MatchEntity>>


}