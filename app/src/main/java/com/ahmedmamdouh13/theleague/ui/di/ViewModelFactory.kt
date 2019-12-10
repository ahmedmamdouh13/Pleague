package com.ahmedmamdouh13.theleague.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedmamdouh13.theleague.ui.di.scope.AppScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@AppScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

