package com.ahmedmamdouh13.theleague.ui.di.module

import com.ahmedmamdouh13.theleague.data.RepositoryImpl
import com.ahmedmamdouh13.theleague.domain.Repository
import com.ahmedmamdouh13.theleague.domain.interactor.MatchesInteractor
import com.ahmedmamdouh13.theleague.domain.usecase.GetMatchesUseCase
import com.ahmedmamdouh13.theleague.ui.di.scope.AppScope
import dagger.Module
import dagger.Provides


@Module
class AppModule {


    @Provides
    @AppScope
    fun providesMatchesInteractor(getMatchesUseCase: GetMatchesUseCase): MatchesInteractor = getMatchesUseCase

    @Provides
    @AppScope
    fun providesRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl


}