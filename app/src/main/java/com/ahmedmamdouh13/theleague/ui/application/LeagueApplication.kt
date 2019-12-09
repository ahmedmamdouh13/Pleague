package com.ahmedmamdouh13.theleague.ui.application

import android.app.Application
import com.ahmedmamdouh13.theleague.ui.di.component.DaggerAppComponent
import com.ahmedmamdouh13.theleague.ui.di.module.AppModule

class LeagueApplication : Application(){

    override fun onCreate() {
        super.onCreate()


    }

    override fun onTerminate() {
        super.onTerminate()
    }

    fun injection() = DaggerAppComponent.builder().appModule(AppModule()).build()


}