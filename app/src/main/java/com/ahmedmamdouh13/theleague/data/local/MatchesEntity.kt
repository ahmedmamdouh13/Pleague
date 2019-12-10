package com.ahmedmamdouh13.theleague.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "matches")
class MatchesEntity {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}