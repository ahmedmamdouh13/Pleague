package com.ahmedmamdouh13.theleague.domain.usecase

import com.ahmedmamdouh13.theleague.domain.Repository
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.domain.mapper.ScheduleMapper
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
import com.ahmedmamdouh13.theleague.domain.util.DateUitl
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(repo: Repository, dateUitl: DateUitl, scheduleMapper: ScheduleMapper) : MatchesInteractor {

    private val repository = repo
    private val util = dateUitl
    private val mapper = scheduleMapper

    override fun getMatches(): Single<Map<String,List<DomainModel>>>{
       return repository.getMatches()
          .map  { domainList ->
              mapper.getMatchesInScheduleForm(domainList
                  .filter {
                      util.isDateValid(it.date)
                  }
                  .map {d ->
                   d.favorite = repository.isMatchFavorite(d.id)
                  d.time = util.getTimeFromUtcDate(d.date)
                  d.date = util.getDateFromUtcDate(d.date)
                   d.days = util.daysUntilDate(d.date)
                      d
              }


              )

          }


    }


    override fun getDaysUntilDate(s: String): String {
        return if (util.isThisDayToday(s)) "TODAY"
        else if (!util.isDateValid(s)) "Ended"
        else util.daysUntilDate(s)
    }

    override fun favoriteFixture(domainModel: DomainModel) {
        repository.favoriteFixture(domainModel)
    }

    override fun unFavoriteFixture(id: Int) {
        repository.unFavoriteFixture(id)
    }

    override fun getFavoriteMatches(): Flowable<List<DomainModel>> {
       return repository.getFavoriteMatches().
               map {
                   it.map {d ->
                       d.days = getDaysUntilDate(d.date)
                       d
                   }
               }
    }
}