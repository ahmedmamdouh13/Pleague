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
    val daysNumberLiveData = MutableLiveData<String>()
    init {
      val d =   useCase.getMatches(30,0)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe { l , e ->
          matchesScheduleLiveData.value = l.mapValues { list ->
              list.value.map {
                  println(it.date + " da b3d b2a ")
                  MatchScheduleModel(
                      it.id,
                      it.date,
                      it.time,
                      it.awayScore,
                      it.awayTeam,
                      it.homeScore,
                      it.homeTeam,
                      it.group
                  )
              }
          }

        }
//        useCase.getMatches(10,10)
        println("Testing appInjection !!")
    }

    fun daysUntilMatch(s: String) {
        daysNumberLiveData.value = useCase.getDaysUntilDate(s)
    }
}