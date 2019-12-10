package com.ahmedmamdouh13.theleague.domain.usecase

import com.ahmedmamdouh13.theleague.domain.Repository
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.domain.mapper.ScheduleMapper
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import com.ahmedmamdouh13.theleague.domain.util.DateUitl
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(repo: Repository, dateUitl: DateUitl, scheduleMapper: ScheduleMapper) : MatchesInteractor {

    private val repository = repo
    private val util = dateUitl
    private val mapper = scheduleMapper

    override fun getMatches(size: Int, index: Int): Single<Map<String,List<DomainModel>>>{
       return repository.getMatches(size, index, util.getTodayInUtc())
          .map  {
              mapper.getMatchesInScheduleForm( it.map {d ->
                  d.time = util.getTimeFromUtcDate(d.date)
                  d.date = util.getDateFromUtcDate(d.date)
                  d
              })
          }
    }


    override fun getDaysUntilDate(s: String): String {
        return if (util.isThisDayToday(s))
            "TODAY"
        else
            util.daysUntilDate(s)
    }

}