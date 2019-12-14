package com.ahmedmamdouh13.theleague.ui.model

data class MatchScheduleModel(
    var id: Int,
    var date: String,
    var time: String,
    var awayScore: Int,
    var awayTeam: String,
    var homeScore: Int,
    var homeTeam: String,
    var group: String,
    var favorite: Boolean,
    var days: String
)