package com.ahmedmamdouh13.theleague.domain.model

data class DomainModel(
    var id: Int,
    var awayTeam: String,
    var homeTeam: String,
    var date: String,
    var time: String,
    var homeScore: Int,
    var awayScore: Int,
    var group: String,
    var favorite: Boolean,
    var days: String
)


