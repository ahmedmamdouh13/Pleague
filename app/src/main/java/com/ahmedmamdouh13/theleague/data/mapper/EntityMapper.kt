package com.ahmedmamdouh13.theleague.data.mapper

import com.ahmedmamdouh13.theleague.data.local.MatchEntity
import com.ahmedmamdouh13.theleague.data.pojo.Match
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import javax.inject.Inject

class EntityMapper @Inject constructor() {

    fun mapMatchToDomain(match: Match): DomainModel {
       return  DomainModel(
           match.id,
           match.awayTeam.name,
           match.homeTeam.name,
           match.utcDate,
           "",
           match.score.fullTime.homeTeam,
           match.score.fullTime.awayTeam,
           match.group,
           false,
           ""

       )
    }

    fun mapDomainToEntity(domainModel: DomainModel)
    = MatchEntity(
        domainModel.id,
        domainModel.homeScore,
        domainModel.awayScore,
        domainModel.date,
        domainModel.homeTeam,
        domainModel.awayTeam,
        domainModel.group,
        domainModel.time,
        domainModel.favorite
    )

    fun mapEntityToDomain(entity: MatchEntity)
    =   DomainModel(
        entity.id,
        entity.awayTeam,
        entity.homeTeam,
        entity.date,
        entity.time,
        entity.homeScore,
        entity.awayScore,
        entity.group,
        entity.isFavorite,
        ""

    )

}
