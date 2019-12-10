package com.ahmedmamdouh13.theleague.domain.usecase

import com.ahmedmamdouh13.theleague.domain.Repository
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(repo: Repository) : MatchesInteractor {

    val repository = repo

    override fun getMatches() {
        repository.getMatches()

    }


}