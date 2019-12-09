package com.ahmedmamdouh13.theleague.ui.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {


    @Provides
    @Singleton
    fun providesRepo():String{

        return "Repo"
    }
}