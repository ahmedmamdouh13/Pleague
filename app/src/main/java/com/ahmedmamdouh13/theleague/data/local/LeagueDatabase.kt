package com.ahmedmamdouh13.theleague.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MatchEntity::class],version = 9,exportSchema = false)
abstract class LeagueDatabase : RoomDatabase() {

    abstract val matchesDao: MatchesDao
}