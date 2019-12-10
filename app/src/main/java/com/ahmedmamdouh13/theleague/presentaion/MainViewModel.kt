package com.ahmedmamdouh13.theleague.presentaion

import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import javax.inject.Inject

class MainViewModel @Inject constructor(matchesInteractor: MatchesInteractor) : ViewModel() {
    private val useCase = matchesInteractor
    fun testFun() {
        useCase.getMatches()
        println("Testing appInjection !!")
    }
}