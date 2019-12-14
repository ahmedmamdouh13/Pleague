package com.ahmedmamdouh13.theleague.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "match")
data class MatchEntity (

    @PrimaryKey(autoGenerate = false) var id :Int = 0,

    var homeScore: Int = -1,

    var awayScore: Int = -1,

    var date: String = "",

    var homeTeam: String = "",

    var awayTeam: String = "",

    var group: String = "",

    var time: String = "",

    var isFavorite: Boolean = false
)
