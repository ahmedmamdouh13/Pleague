package com.ahmedmamdouh13.theleague.ui.custom

import android.content.res.Resources
import android.view.MotionEvent
import android.view.View
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

class ScreenTouchListener @Inject constructor() : View.OnTouchListener {
    private lateinit var gestures: TouchGestures
    private val deviceHeight = Resources.getSystem().displayMetrics.heightPixels
    private val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    var xPerPixel = 0f
    var yPerPixel = 0f
    var  xDifference = 0f
    var yDifference = 0f
     val TOP_LIMIT = 0.4F
    val BOTTM_LIMIT = 0.925F
    val LEFT_LIMIT = 0.7F
    val RIGHT_LIMIT = 0.975F
    override fun onTouch(v: View, event: MotionEvent): Boolean {


        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                println("Down x : ${event.x } , y : ${event.y}")
                xPerPixel = event.rawX
                yPerPixel = event.rawY
                 xDifference =  v.x - xPerPixel
                yDifference =  v.x - yPerPixel
            }
            MotionEvent.ACTION_MOVE ->{
               val  rawX = event.rawX
               val rawY= event.rawY


//                if (yPerPixel + rawX  < 1  && xPerPixel + rawY < 1)
                val percentTop = max(0F, min(TOP_LIMIT, (yDifference + rawY) / deviceHeight)) / TOP_LIMIT
                val percentBottom = max(0F, min(BOTTM_LIMIT, (yDifference + rawY) / deviceHeight)) / BOTTM_LIMIT
                val percentLeft = max(0F, min(LEFT_LIMIT, (xDifference + rawX) / deviceWidth)) / LEFT_LIMIT
                val percentRight = max(0F, min(RIGHT_LIMIT, (xDifference + rawY) / deviceWidth)) / RIGHT_LIMIT
                println("Move Top : ${percentTop} , Bottom : ${percentBottom}  Left ${percentLeft} , Right : ${percentRight}")
                if (percentTop < BOTTM_LIMIT)
                gestures.scroll(percentTop, percentBottom, percentTop * LEFT_LIMIT, percentRight)

            }
            MotionEvent.ACTION_UP ->{
                println("Up x : ${event.x} , y : ${event.y}")

            }
            MotionEvent.ACTION_SCROLL ->{
                println("Scroll x : ${event.x} , y : ${event.y}")

            }
        }


        //v?.performClick()


        return true
    }


    fun setTouchGestures (gestures: TouchGestures){
        this.gestures = gestures
    }

}