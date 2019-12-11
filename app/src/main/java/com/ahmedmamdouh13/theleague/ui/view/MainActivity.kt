package com.ahmedmamdouh13.theleague.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.adapter.MatchesScheduleRecyclerAdapter
import com.ahmedmamdouh13.theleague.ui.model.LottieAnimationsRaw
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.date_in_lottie_layout.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        viewModel.checkToggleListener.observe(this,viewModel.checkObserver)
        viewModel.unCheckToggleListener.observe(this, viewModel.unCheckObserver)

        val adapter = MatchesScheduleRecyclerAdapter().apply {
            setListener(viewModel.checkToggleListener,viewModel.unCheckToggleListener)
            viewModel.matchesScheduleLiveData.observe(this@MainActivity, Observer {
                list = it
                notifyDataSetChanged()
                viewModel.daysUntilMatch(it.keys.toList()[0])

            })
        }
        var cntFlag = 0

        matchesschedule_recyclerview_mainactivity.itemAnimator = DefaultItemAnimator()
        matchesschedule_recyclerview_mainactivity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        matchesschedule_recyclerview_mainactivity.adapter = adapter



        favorite_screen_activitymain.setOnClickListener {
            TransitionManager.beginDelayedTransition(favorite_screen_container_activitymain)
            left_guideline.setGuidelinePercent(0f)
            right_guideline.setGuidelinePercent(1f)
            top_guideline.setGuidelinePercent(0f)
            bottom_guideline.setGuidelinePercent(1f)
        }


        matchesschedule_recyclerview_mainactivity.addOnScrollListener(object :
                RecyclerView.OnScrollListener(){

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val itemPosition =
                    (matchesschedule_recyclerview_mainactivity.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                if (cntFlag != itemPosition) {


                    val date1 = adapter.list.keys.toList()[itemPosition]
                    viewModel.daysUntilMatch(date1)

                    val arr =  date1.split("-")
                    val date = arr[0]+arr[1]+arr[2]
                    for (c in date.indices){
                        when(c) {
                            0 -> {
                             lottie_linearlayout_container.number_1_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_1_lottieview_itemmatchschedule.playAnimation()

                            }
                            1 -> {
                                lottie_linearlayout_container.   number_2_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_2_lottieview_itemmatchschedule.playAnimation()

                            }
                            2 -> {
                                lottie_linearlayout_container.number_3_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_3_lottieview_itemmatchschedule.playAnimation()

                            }
                            3 -> {
                                lottie_linearlayout_container.number_4_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_4_lottieview_itemmatchschedule.playAnimation()

                            }
                            4 -> {
                                lottie_linearlayout_container.number_5_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_5_lottieview_itemmatchschedule.playAnimation()

                            }
                            5 -> {
                                lottie_linearlayout_container.number_6_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_6_lottieview_itemmatchschedule.playAnimation()

                            }
                            6 -> {
                                lottie_linearlayout_container.number_7_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_7_lottieview_itemmatchschedule.playAnimation()

                            }
                            7 -> {
                                lottie_linearlayout_container.number_8_lottieview_itemmatchschedule
                                .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                                lottie_linearlayout_container.number_8_lottieview_itemmatchschedule.playAnimation()

                            }
                        }
                    }
                }
                cntFlag = itemPosition

            }


            })


        viewModel.daysNumberLiveData.observe(this, Observer {

            if (it.length > 1) {
                daysleft_lottie_mainactivity.setAnimation(LottieAnimationsRaw.getRawFile(it[0].toString()))
                daysleft_lottie_mainactivity.playAnimation()
                daysright_lottie_mainactivity.setAnimation(LottieAnimationsRaw.getRawFile(it[1].toString()))
                daysright_lottie_mainactivity.playAnimation()
            }
            else {
                daysleft_lottie_mainactivity.setAnimation(LottieAnimationsRaw.getRawFile("0"))
                daysleft_lottie_mainactivity.playAnimation()
                daysright_lottie_mainactivity.setAnimation(LottieAnimationsRaw.getRawFile(it))
                daysright_lottie_mainactivity.playAnimation()
            }
        })



    }

    private fun init() {
        (application as LeagueApplication)
            .appInjection()
            .inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]

    }
}
