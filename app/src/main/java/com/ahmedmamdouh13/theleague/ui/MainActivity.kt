package com.ahmedmamdouh13.theleague.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.ui.di.ViewModelFactory
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        val viewModel = ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]



    }

    private fun init() {
        (application as LeagueApplication)
            .injection()
            .inject(this)
    }
}
