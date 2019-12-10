package com.ahmedmamdouh13.theleague.domain.usecase

import com.ahmedmamdouh13.theleague.domain.Repository
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.domain.util.DateUitl
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(repo: Repository, dateUitl: DateUitl) : MatchesInteractor {

    val repository = repo
    val util = dateUitl

    override fun getMatches(size: Int, index: Int) {
        val d =  repository.getMatches(size, index, util.getTodayInUtc())
          .subscribe ({


              println(it.size)
              for (d in it) {
                  println(util.getTimeFromUtcDate(d.date))
                  println(util.isThisDayToday(d.date))
                  println(d.score)
              }
              println(util.getTimeFromUtcDate(it.last().date))

          },{ t ->
              t.printStackTrace()
          })


    }


}