package com.ahmedmamdouh13.theleague.presentaion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(matchesInteractor: MatchesInteractor) : ViewModel() {
    private val useCase = matchesInteractor
    val matchesScheduleLiveData = MutableLiveData<Map<String,List<MatchScheduleModel>>>()
    fun testFun() {
      val d =   useCase.getMatches(10,0)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe { l , e ->

          matchesScheduleLiveData.value = l.mapValues { list ->
              list.value.map {
                  println(list.key)
                  MatchScheduleModel(
                      it.id,
                      it.date,
                      it.time,
                      it.awayScore,
                      it.awayTeam,
                      it.homeScore,
                      it.homeTeam
                  )
              }
          }

        }
//        useCase.getMatches(10,10)
        println("Testing appInjection !!")
    }

    fun daysUntilMatch(itemPosition: Int, s: String) {
        println(useCase.getDaysUntilDate(s))
    }
}