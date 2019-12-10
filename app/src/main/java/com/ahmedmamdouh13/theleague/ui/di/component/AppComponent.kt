package com.ahmedmamdouh13.theleague.ui.di.component

import com.ahmedmamdouh13.theleague.ui.MainActivity
import com.ahmedmamdouh13.theleague.ui.di.module.AppModule
import com.ahmedmamdouh13.theleague.ui.di.module.DataModule
import com.ahmedmamdouh13.theleague.ui.di.module.ViewModelModule
import com.ahmedmamdouh13.theleague.ui.di.scope.AppScope
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [AppModule::class, ViewModelModule::class,DataModule::class])
interface AppComponent  {

    fun inject(activity: MainActivity)
}