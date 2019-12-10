package com.ahmedmamdouh13.theleague.ui.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.adapter.MatchesScheduleRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        val adapter = MatchesScheduleRecyclerAdapter().apply {
            viewModel.matchesScheduleLiveData.observe(this@MainActivity, Observer {
                list = it
                notifyDataSetChanged()
            })
        }
        viewModel.testFun()
        var cntFlag = 0

        matchesschedule_recyclerview_mainactivity.itemAnimator = DefaultItemAnimator()
        matchesschedule_recyclerview_mainactivity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        matchesschedule_recyclerview_mainactivity.adapter = adapter
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            matchesschedule_recyclerview_mainactivity.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->


                val itemPosition =
                    (matchesschedule_recyclerview_mainactivity.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                if (cntFlag != itemPosition) {
                    viewModel.daysUntilMatch(itemPosition, adapter.list.keys.toList()[itemPosition])
                }
                cntFlag = itemPosition
            }
        }


    }

    private fun init() {
        (application as LeagueApplication)
            .appInjection()
            .inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]

    }
}
