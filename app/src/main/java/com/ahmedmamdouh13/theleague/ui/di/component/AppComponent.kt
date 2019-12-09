package com.ahmedmamdouh13.theleague.ui.di.component

import com.ahmedmamdouh13.theleague.ui.MainActivity
import com.ahmedmamdouh13.theleague.ui.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent  {

    fun inject(activity: MainActivity)
}