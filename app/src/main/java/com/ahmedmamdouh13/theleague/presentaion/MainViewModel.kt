package com.ahmedmamdouh13.theleague.presentaion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    matchesInteractor: MatchesInteractor
) : ViewModel() {
    private val useCase = matchesInteractor
    val matchesScheduleLiveData = MutableLiveData<Map<String,List<MatchScheduleModel>>>()
    val daysNumberLiveData = MutableLiveData<String>()
    val dateChangedLiveData = MutableLiveData<String>()
    val checkToggleListener: MutableLiveData<Int> = MutableLiveData()
    val unCheckToggleListener: MutableLiveData<Int> = MutableLiveData()


    init {
      val d =   useCase.getMatches(30,0)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe { l , e ->
          matchesScheduleLiveData.value = l.mapValues { list ->
              list.value.map {
                  println(it.date + " da b3d b2a " + list.key)
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
//     val disposable =   useCase.getFavoriteMatches()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                it.map {
//                    println("${it.favorite} ${it.id}")
//                }
//            }
        println("Testing appInjection !!")
    }

    fun daysUntilMatch(date: String) {
        daysNumberLiveData.value = useCase.getDaysUntilDate(date)
        dateChangedLiveData.value = date
    }


    val checkObserver = Observer<Int>{id ->
        println("Checked $id")
        Single.create<Unit> {
            useCase.favoriteFixture(id)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
    val unCheckObserver = Observer<Int>{ id ->
        println("Unchecked $id")
        Single.create<Unit> {
            useCase.unFavoriteFixture(id)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

}