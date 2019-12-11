package com.ahmedmamdouh13.theleague.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener
import com.ahmedmamdouh13.theleague.ui.custom.TouchGestures
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() , TouchGestures {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var touchListener: ScreenTouchListener

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     init()

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.matches_screen_activitymain,MatchesFragment())
beginTransaction.add(R.id.favorite_screen_activitymain,FavoriteFragment())
        beginTransaction.commit()

//        favorite_screen_activitymain.setOnClickListener {
//            TransitionManager.beginDelayedTransition(favorite_screen_container_activitymain)
//            left_guideline.setGuidelinePercent(0f)
//            right_guideline.setGuidelinePercent(1f)
//            top_guideline.setGuidelinePercent(0f)
//            bottom_guideline.setGuidelinePercent(1f)
//        }
        touchListener.setTouchGestures(this)
        favorite_screen_activitymain.setOnTouchListener(touchListener)

    }

    private fun init() {
        (application as LeagueApplication)
            .appInjection()
            .inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]

    }

    override fun click() {
    }

    override fun swipe(percent: Float) {
    }

    override fun scroll(
        percentTop: Float,
        percentBottom: Float,
        percentLeft: Float,
        percentRight: Float
    ) {

        left_guideline.setGuidelinePercent(percentLeft)
//            right_guideline.setGuidelinePercent(percentRight)
            top_guideline.setGuidelinePercent(percentTop)
//            bottom_guideline.setGuidelinePercent(percentBottom)
    }

    override fun expand(isExpanded: Boolean) {
    }
}
