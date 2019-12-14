package com.ahmedmamdouh13.theleague.presentaion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.domain.model.DomainModel
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
    val matchesFavoriteLiveData = MutableLiveData<List<MatchScheduleModel>>()
    val daysNumberLiveData = MutableLiveData<String>()
    val dateChangedLiveData = MutableLiveData<String>()
    val checkToggleListener: MutableLiveData<MatchScheduleModel> = MutableLiveData()
    val unCheckToggleListener: MutableLiveData<Int> = MutableLiveData()
    val matchesState: MutableLiveData<UiState> = MutableLiveData()


    init {

        matchesState.value = UiState.Loading

        val d =     useCase.getMatches()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { l , e ->
                //                if (l != null)
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
                            it.group,
                            it.favorite,
                            it.days
                        )
                    }
                }
                matchesState.value = UiState.Success

            }
//        useCase.getMatches(10,10)
     val disposable =   useCase.getFavoriteMatches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {list ->
                matchesFavoriteLiveData.value = list.map {
                    println("${it.favorite} ${it.id}")
                   MatchScheduleModel(
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
                }
            }
        println("Testing appInjection !!")
    }

    fun daysUntilMatch(date: String) {
        daysNumberLiveData.value = useCase.getDaysUntilDate(date)
        dateChangedLiveData.value = date
    }


    val checkObserver = Observer<MatchScheduleModel>{model ->
        println("Checked ${model.id}")
        Single.create<Unit> {
            useCase.favoriteFixture(
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

                ))
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