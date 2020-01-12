package com.ahmedmamdouh13.cornertoview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.AutoTransition
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener
import com.ahmedmamdouh13.theleague.ui.custom.TouchGestures
import kotlinx.android.synthetic.main.controller.view.*

class Controller constructor(
    private val first: Fragment, private val second: Fragment,
    private val fmanager: FragmentManager):
    Fragment(),
    TouchGestures {

private val touchListener:ScreenTouchListener = ScreenTouchListener()


    lateinit var mView: View
    var firstIcon:Int = R.drawable.ic_tab_black_24dp
    var secondIcon:Int = R.drawable.ic_tab_black_24dp
        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.controller, null, false)


        fmanager.beginTransaction().replace(R.id.favorite_screen_activitymain,first)
            .replace(R.id.matches_screen_activitymain,second).commit()


        mView.matches_imageview_mainactivity.setImageDrawable(ContextCompat.getDrawable(context!!,firstIcon))
        mView.matches_imageview_mainactivity.setImageDrawable(ContextCompat.getDrawable(context!!,secondIcon))


        touchListener.setTouchGestures(this)
        mView.favorite_imageview_mainactivity.setOnTouchListener(touchListener)


        return mView
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
            mView.favorite_imageview_mainactivity.id ->{
                mView.left_guideline.setGuidelinePercent(percentLeft)
                mView.top_guideline.setGuidelinePercent(percentTop)
                mView.favorite_imageview_mainactivity.visibility = View.VISIBLE
                mView.favorite_imageview_mainactivity.alpha =  percentTop
                mView.matches_screen_activitymain.alpha = percentTop
            }
            else ->{
                mView.left_guideline_matches.setGuidelinePercent(percentLeft)
                mView.top_guideline_matches.setGuidelinePercent(percentTop)
                mView.matches_imageview_mainactivity.visibility = View.VISIBLE
                mView.matches_imageview_mainactivity.alpha =  percentTop
                mView.favorite_screen_activitymain.alpha = percentTop
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
            mView.favorite_imageview_mainactivity.isEnabled = true
            mView.matches_imageview_mainactivity.isEnabled = true
        }
        override fun onTransitionStart(transition: Transition) {
            mView.favorite_imageview_mainactivity.isEnabled = false
            mView.matches_imageview_mainactivity.isEnabled = false
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
            mView.favorite_imageview_mainactivity.id ->{
                mView.matches_imageview_mainactivity.bringToFront()
                if (isScrolled)
                    mView.matches_screen_activitymain.visibility = View.INVISIBLE
                TransitionManager
                    .beginDelayedTransition(mView.favorite_screen_container_activitymain,autoTransition)
                mView.left_guideline.setGuidelinePercent(0f)
                mView.top_guideline.setGuidelinePercent(0f)
                mView.left_guideline_matches.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                mView.top_guideline_matches.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)

                transitionSettings(mView.favorite_screen_activitymain,
                    mView.matches_screen_activitymain,
                    mView.favorite_imageview_mainactivity,
                    mView.matches_imageview_mainactivity)
            }
            else ->{

                if (isScrolled)
                    mView.favorite_screen_activitymain.visibility = View.INVISIBLE

                mView.favorite_imageview_mainactivity.bringToFront()
                TransitionManager
                    .beginDelayedTransition(mView.favorite_screen_container_activitymain,autoTransition)

                mView.left_guideline.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                mView.top_guideline.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
                mView.left_guideline_matches.setGuidelinePercent(0f)
                mView.top_guideline_matches.setGuidelinePercent(0f)

                transitionSettings(
                    mView.matches_screen_activitymain,
                    mView.favorite_screen_activitymain,
                    mView.matches_imageview_mainactivity,
                    mView.favorite_imageview_mainactivity
                )

            }
        }
        isScrolled = false
    }

    fun transitionSettings(containerExpand: View,
                           containerCollapse: View,
                           overlayExpand: View,
                           overlayCollapse: View
    ){
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
            .beginDelayedTransition(mView.favorite_screen_container_activitymain)
        when(id){
            mView.matches_imageview_mainactivity.id ->{
            mView.    left_guideline.setGuidelinePercent(0f)
            mView.    top_guideline.setGuidelinePercent(0f)
            mView.    left_guideline_matches.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
            mView.    top_guideline_matches.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
            mView.    favorite_imageview_mainactivity.alpha = 0f
            mView.    matches_imageview_mainactivity.setOnTouchListener(touchListener)
            mView.    favorite_imageview_mainactivity.setOnTouchListener(null)
            mView.    matches_screen_activitymain.bringToFront()
            mView.    matches_screen_activitymain.alpha = 1f
            mView.    matches_imageview_mainactivity.alpha = 1f

            }
            else ->{
                mView.left_guideline.setGuidelinePercent(ScreenTouchListener.RIGHT_LIMIT)
                mView.top_guideline.setGuidelinePercent(ScreenTouchListener.BOTTM_LIMIT)
                mView.left_guideline_matches.setGuidelinePercent(0f)
                mView.top_guideline_matches.setGuidelinePercent(0f)
                mView.favorite_imageview_mainactivity.alpha = 1f
                mView.matches_imageview_mainactivity.setOnTouchListener(null)
                mView.favorite_imageview_mainactivity.setOnTouchListener(touchListener)
                mView.favorite_screen_activitymain.bringToFront()
                mView.favorite_screen_activitymain.alpha = 1f
                mView.matches_imageview_mainactivity.alpha = 0f
            }
        }

        isScrolled = false
    }


    fun setFirstCollapseIcon(@DrawableRes icon: Int) {
        mView.matches_imageview_mainactivity.setImageDrawable(ContextCompat.getDrawable(context!!,icon))
    }

    fun setSecondCollapseIcon(@DrawableRes icon: Int) {
        mView.favorite_imageview_mainactivity.setImageDrawable(ContextCompat.getDrawable(context!!,icon))
    }
    fun setFirstCollapseColor(@ColorRes color: Int){
        mView.matches_imageview_mainactivity.setBackgroundColor(ContextCompat.getColor(context!!,color))
    }
    fun setSecondCollapseColor(@ColorRes color: Int){
        mView.favorite_imageview_mainactivity.setBackgroundColor(ContextCompat.getColor(context!!,color))
    }
}