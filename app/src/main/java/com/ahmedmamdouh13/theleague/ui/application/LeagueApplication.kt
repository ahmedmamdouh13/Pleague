package com.ahmedmamdouh13.theleague.ui.application

import android.app.Application
import com.ahmedmamdouh13.theleague.ui.di.component.AppComponent
import com.ahmedmamdouh13.theleague.ui.di.component.DaggerAppComponent
import com.ahmedmamdouh13.theleague.ui.di.module.AppModule
import com.ahmedmamdouh13.theleague.ui.di.module.DataModule

class LeagueApplication : Application(){

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

//        DaggerDataComponent.builder().dataModule(DataModule(this)).build()
//        DaggerAppComponent.builder().appModule(AppModule()).build()
         appComponent = DaggerAppComponent.builder()
             .appModule(AppModule())
             .dataModule(DataModule(this))
             .build()

    }

    override fun onTerminate() {
        super.onTerminate()
    }

    fun appInjection() = appComponent
//        .plus(DataModule(this))

}