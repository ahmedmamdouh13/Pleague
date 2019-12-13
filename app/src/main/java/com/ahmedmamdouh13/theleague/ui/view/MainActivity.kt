package com.ahmedmamdouh13.theleague.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener
import com.ahmedmamdouh13.theleague.ui.custom.TouchGestures
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.transition.ChangeBounds
import android.view.WindowManager
import androidx.transition.*
import com.ahmedmamdouh13.theleague.R

class MainActivity : AppCompatActivity() , TouchGestures {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var touchListener: ScreenTouchListener

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
     init()

        supportFragmentManager.beginTransaction()
            .replace(R.id.matches_screen_activitymain,MatchesFragment())
            .replace(R.id.favorite_screen_activitymain,FavoriteFragment()).commit()

//        favorite_screen_activitymain.setOnClickListener {
//            TransitionManager.beginDelayedTransition(favorite_screen_container_activitymain)
//            left_guideline.setGuidelinePercent(0f)
//            right_guideline.setGuidelinePercent(1f)
//            top_guideline.setGuidelinePercent(0f)
//            bottom_guideline.setGuidelinePercent(1f)
//        }
        touchListener.setTouchGestures(this)
        favorite_imageview_mainactivity.setOnTouchListener(touchListener)

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

    var isScrolled = false
    override fun scroll(
        percentTop: Float,
        percentBottom: Float,
        percentLeft: Float,
        percentRight: Float,
        id: Int
    ) {
when(id){
    favorite_imageview_mainactivity.id ->{
        left_guideline.setGuidelinePercent(percentLeft)
        top_guideline.setGuidelinePercent(percentTop)
        favorite_imageview_mainactivity.visibility = View.VISIBLE
        favorite_imageview_mainactivity.alpha =  percentTop
        matches_screen_activitymain.alpha = percentTop
    }
    else ->{
        left_guideline_matches.setGuidelinePercent(percentLeft)
        top_guideline_matches.setGuidelinePercent(percentTop)
        matches_imageview_mainactivity.visibility = View.VISIBLE
        matches_imageview_mainactivity.alpha =  percentTop
        favorite_screen_activitymain.alpha = percentTop
    }
}

        isScrolled = true

//        matches_screen_activitymain.translationZ = percentTop
//        left_guideline_matches.setGuidelinePercent(1 - (percentLeft / 2))
//        top_guideline_matches.setGuidelinePercent(1 - (percentTop / 2))
//        bottom_guideline_matches.setGuidelinePercent(1 - (percentBottom / 2))
//        right_guideline_matches.setGuidelinePercent(1 - (percentRight / 2))
    }

    private val autoTransition = AutoTransition().apply {
        duration = 250
    }

    val transitionSet = autoTransition.addListener(object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition) {
            favorite_imageview_mainactivity.isEnabled = true
            matches_imageview_mainactivity.isEnabled = true
        }

        override fun onTransitionResume(transition: Transition) {

        }

        override fun onTransitionPause(transition: Transition) {
        }

        override fun onTransitionCancel(transition: Transition) {
        }

        override fun onTransitionStart(transition: Transition) {
            favorite_imageview_mainactivity.isEnabled = false
            matches_imageview_mainactivity.isEnabled = false
        }

    })

    override fun expand(id: Int) {


        when(id){
            favorite_imageview_mainactivity.id ->{

                matches_imageview_mainactivity.bringToFront()
                if (isScrolled)
                matches_screen_activitymain.visibility = View.INVISIBLE
                TransitionManager
                    .beginDelayedTransition(favorite_screen_container_activitymain,autoTransition)

                left_guideline.setGuidelinePercent(0f)
                top_guideline.setGuidelinePercent(0f)
                left_guideline_matches.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                top_guideline_matches.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
//                favorite_imageview_mainactivity.alpha = 0f
                matches_imageview_mainactivity.setOnTouchListener(touchListener)
                favorite_imageview_mainactivity.setOnTouchListener(null)
                matches_screen_activitymain.bringToFront()
                matches_screen_activitymain.alpha = 1f
                matches_screen_activitymain.visibility = View.VISIBLE
                matches_imageview_mainactivity.visibility = View.VISIBLE
                favorite_imageview_mainactivity.visibility = View.GONE
                matches_imageview_mainactivity.alpha = 1f

            }
            else ->{
                if (isScrolled)
                favorite_screen_activitymain.visibility = View.INVISIBLE
                favorite_imageview_mainactivity.bringToFront()

                TransitionManager
                    .beginDelayedTransition(favorite_screen_container_activitymain,autoTransition)
                left_guideline.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                top_guideline.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
                left_guideline_matches.setGuidelinePercent(0f)
                top_guideline_matches.setGuidelinePercent(0f)
                favorite_imageview_mainactivity.alpha = 1f
                matches_imageview_mainactivity.setOnTouchListener(null)
                favorite_imageview_mainactivity.setOnTouchListener(touchListener)
                favorite_screen_activitymain.bringToFront()
                favorite_screen_activitymain.alpha = 1f
                favorite_screen_activitymain.visibility = View.VISIBLE
                favorite_imageview_mainactivity.visibility = View.VISIBLE
                matches_imageview_mainactivity.visibility = View.GONE


            }
        }
        isScrolled = false

    }

    override fun collapse(id: Int) {
        TransitionManager
            .beginDelayedTransition(favorite_screen_container_activitymain)
        when(id){
            matches_imageview_mainactivity.id ->{
                left_guideline.setGuidelinePercent(0f)
                top_guideline.setGuidelinePercent(0f)
                left_guideline_matches.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                top_guideline_matches.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
                favorite_imageview_mainactivity.alpha = 0f
                matches_imageview_mainactivity.setOnTouchListener(touchListener)
                favorite_imageview_mainactivity.setOnTouchListener(null)
                matches_screen_activitymain.bringToFront()
                matches_screen_activitymain.alpha = 1f
                matches_imageview_mainactivity.alpha = 1f

            }
            else ->{
                left_guideline.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                top_guideline.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
                left_guideline_matches.setGuidelinePercent(0f)
                top_guideline_matches.setGuidelinePercent(0f)
                favorite_imageview_mainactivity.alpha = 1f
                matches_imageview_mainactivity.setOnTouchListener(null)
                favorite_imageview_mainactivity.setOnTouchListener(touchListener)
                favorite_screen_activitymain.bringToFront()
                favorite_screen_activitymain.alpha = 1f
                matches_imageview_mainactivity.alpha = 0f
            }
        }
        isScrolled = false
    }
}

