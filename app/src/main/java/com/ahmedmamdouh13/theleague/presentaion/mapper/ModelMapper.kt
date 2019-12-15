package com.ahmedmamdouh13.theleague.presentaion.mapper

import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import javax.inject.Inject

class ModelMapper @Inject constructor(){


    fun mapDomainToModel(it: DomainModel)
    =  MatchScheduleModel(
        it.id,
        it.date,
        it.time,
        it.awayScore,
        it.awayTeam,
        it.homeScore,
        it.homeTeam,
        it.group,
        it.favorite,
        it.days
    )

    fun mapModelToDomain(model: MatchScheduleModel): DomainModel =
        DomainModel(
        model.id,
        model.awayTeam,
        model.homeTeam,
        model.date,
        model.time,
        model.homeScore,
        model.awayScore,
        model.group,
        model.favorite,
        ""
        )
}