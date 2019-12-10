package com.ahmedmamdouh13.theleague.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "match")
class MatchEntity {

    @PrimaryKey(autoGenerate = false)
    var id = 0

    var score = ""

    var date = ""

    var homeTeam = ""

    var awayTeam = ""
}