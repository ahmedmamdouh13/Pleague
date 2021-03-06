package com.ahmedmamdouh13.theleague.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.presentaion.UiState
import com.ahmedmamdouh13.theleague.ui.adapter.MatchesScheduleRecyclerAdapter
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.ui.model.LottieAnimationsRaw
import com.ahmedmamdouh13.theleague.ui.util.InternetConnection
import kotlinx.android.synthetic.main.date_in_lottie_layout.view.*
import kotlinx.android.synthetic.main.matches_screen.*
import kotlinx.android.synthetic.main.matches_screen.view.*
import javax.inject.Inject

class MatchesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        val view = inflater.inflate(R.layout.matches_screen, null, false)
        if (InternetConnection.isNetworkAvailable(context!!)) {
            viewModel.initMatches()
        }
        viewModel.matchesState.observe(this, Observer {
            when(it){
                UiState.Loading -> view.progress_matchesfragment.visibility = View.VISIBLE
                UiState.Success -> view.progress_matchesfragment.visibility = View.INVISIBLE
                UiState.Error -> {
                    view.progress_matchesfragment.visibility = View.INVISIBLE
                }


            }
        })

        viewModel.checkToggleListener.observe(this,viewModel.checkObserver)
        viewModel.unCheckToggleListener.observe(this, viewModel.unCheckObserver)

        viewModel.matchesState.observe(this, Observer {
            when(it){
                UiState.Loading -> {
                    lottie_linearlayout_container.visibility = View.INVISIBLE
                }
                else ->{
                    lottie_linearlayout_container.visibility = View.VISIBLE
                }
                }
        })

        val adapter = MatchesScheduleRecyclerAdapter().apply {
            setListener(viewModel.checkToggleListener,viewModel.unCheckToggleListener)
            viewModel.matchesScheduleLiveData.observe(this@MatchesFragment, Observer {
                list = it
                notifyDataSetChanged()
                viewModel.daysUntilMatch(it.keys.toList().first())
            })



        }

        var cntFlag = 0

       view.matchesschedule_recyclerview_mainactivity.itemAnimator = DefaultItemAnimator()
       view.matchesschedule_recyclerview_mainactivity.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
       view.matchesschedule_recyclerview_mainactivity.adapter = adapter






       view.matchesschedule_recyclerview_mainactivity.addOnScrollListener(object :
            RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val itemPosition =
                    (view.matchesschedule_recyclerview_mainactivity.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                if (cntFlag != itemPosition && itemPosition >= 0) {
                    val date1 = adapter.list.keys.toList()[itemPosition]
                    viewModel.daysUntilMatch(date1)
                }

                cntFlag = itemPosition
            }


        })
        var current = "aaaa-aa-aa"

        viewModel.dateChangedLiveData.observe(this, Observer { date1 ->


            val arr =  date1.split("-")
            val arrCurrent = current.split("-")
            val currentDate = arrCurrent[0]+arrCurrent[1]+arrCurrent[2]
            val date = arr[0]+arr[1]+arr[2]
            for (c in date.indices){
                if (date[c] != currentDate[c])
                when(c) {
                    0 -> {
                        view.lottie_linearlayout_container.number_1_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_1_lottieview_itemmatchschedule.playAnimation()

                    }
                    1 -> {
                        view. lottie_linearlayout_container.number_2_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view. lottie_linearlayout_container.number_2_lottieview_itemmatchschedule.playAnimation()

                    }
                    2 -> {
                        view.lottie_linearlayout_container.number_3_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_3_lottieview_itemmatchschedule.playAnimation()

                    }
                    3 -> {
                        view.lottie_linearlayout_container.number_4_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_4_lottieview_itemmatchschedule.playAnimation()

                    }
                    4 -> {
                        view.lottie_linearlayout_container.number_5_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_5_lottieview_itemmatchschedule.playAnimation()

                    }
                    5 -> {
                        view.lottie_linearlayout_container.number_6_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_6_lottieview_itemmatchschedule.playAnimation()

                    }
                    6 -> {
                        view.lottie_linearlayout_container.number_7_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_7_lottieview_itemmatchschedule.playAnimation()

                    }
                    7 -> {
                        view.lottie_linearlayout_container.number_8_lottieview_itemmatchschedule
                            .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                        view.lottie_linearlayout_container.number_8_lottieview_itemmatchschedule.playAnimation()

                    }
                }
            }

            current = date1
        })

        var currentDays = "aa"

        viewModel.daysNumberLiveData.observe(this, Observer {days ->

            if(days == "TODAY"){
                today_textview_matchesscreen.visibility = View.VISIBLE
                daysuntil_container_matchesscreen.visibility = View.INVISIBLE
            }else {
                today_textview_matchesscreen.visibility = View.INVISIBLE
                daysuntil_container_matchesscreen.visibility = View.VISIBLE
                if (currentDays[0] != days[0]) {
                    view.daysleft_lottie_mainactivity.setAnimation(
                        LottieAnimationsRaw.getRawFile(
                            days[0].toString()
                        )
                    )
                    view.daysleft_lottie_mainactivity.playAnimation()
                }
                view.daysright_lottie_mainactivity.setAnimation(LottieAnimationsRaw.getRawFile(days[1].toString()))
                view.daysright_lottie_mainactivity.playAnimation()
            }
            currentDays = days
        })





        return view
    }

    private fun init() {
        (activity?.application as LeagueApplication)
            .appInjection()
            .inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]

    }
}