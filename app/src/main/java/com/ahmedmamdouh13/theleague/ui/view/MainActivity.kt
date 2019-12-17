package com.ahmedmamdouh13.theleague.ui.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.AutoTransition
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener
import com.ahmedmamdouh13.theleague.ui.custom.TouchGestures
import com.ahmedmamdouh13.theleague.ui.util.InternetConnection
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() , TouchGestures {

    @Inject
    lateinit var touchListener: ScreenTouchListener
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
     init()



        if (!InternetConnection.isNetworkAvailable(this)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.favorite_screen_activitymain,FavoriteFragment()).commit()
            expand(R.id.favorite_imageview_mainactivity)
            matches_imageview_mainactivity.setOnTouchListener(null)
            matches_imageview_mainactivity.setOnClickListener {
                if (!InternetConnection.isNetworkAvailable(this))
                showNoConnectionMsg("No Internet !")
                else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.matches_screen_activitymain,MatchesFragment()).commit()
                    expand(R.id.matches_imageview_mainactivity)
                    it.setOnClickListener(null)
                    it.setOnTouchListener(touchListener)

                }
            }
        }
        else
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.matches_screen_activitymain,MatchesFragment())
                .replace(R.id.favorite_screen_activitymain,FavoriteFragment()).commit()
        }


        touchListener.setTouchGestures(this)
        favorite_imageview_mainactivity.setOnTouchListener(touchListener)

    }

    private fun showNoConnectionMsg(msg: String) {
        Snackbar.make(main_container_mainactivity,msg,Snackbar.LENGTH_LONG).show()

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

    private var isScrolled = false
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

    }.addListener(object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition) {
            favorite_imageview_mainactivity.isEnabled = true
            matches_imageview_mainactivity.isEnabled = true
        }
        override fun onTransitionStart(transition: Transition) {
            favorite_imageview_mainactivity.isEnabled = false
            matches_imageview_mainactivity.isEnabled = false
        }

        override fun onTransitionResume(transition: Transition) {

        }

        override fun onTransitionPause(transition: Transition) {
        }

        override fun onTransitionCancel(transition: Transition) {
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

                transitionSettings(favorite_screen_activitymain,
                    matches_screen_activitymain,
                    favorite_imageview_mainactivity,
                    matches_imageview_mainactivity)
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

                transitionSettings(
                    matches_screen_activitymain,
                    favorite_screen_activitymain,
                    matches_imageview_mainactivity,
                    favorite_imageview_mainactivity
                )

            }
        }
        isScrolled = false
    }

    fun transitionSettings(containerExpand: View,
                           containerCollapse: View,
                           overlayExpand: View,
                           overlayCollapse: View){
        overlayCollapse.setOnTouchListener(touchListener)
        overlayExpand.setOnTouchListener(null)
        containerCollapse.bringToFront()
        containerCollapse.alpha = 1f
        containerCollapse.visibility = View.VISIBLE
        overlayCollapse.visibility = View.VISIBLE
        overlayCollapse.alpha = 1f
        overlayExpand.visibility = View.GONE
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

