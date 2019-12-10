package com.ahmedmamdouh13.theleague.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.Constants.notAvailable

class ScheduleView(context: Context, attrs: AttributeSet?) : FrameLayout(context,attrs) {
    constructor(context: Context) : this(context,null)

    var paint=Paint()
    var textPaint=Paint()
    var mx=0f
    var my=0f
    var team1Name="team1"
    var team2Name="team2"
    var textSize=30f

    var isMatchPlayed1 = false

    var datetextMatch1= notAvailable
    var teamsgroup= notAvailable
    var matchResult1= notAvailable




    init {
        this.setWillNotDraw(false)
        paint.color = ContextCompat.getColor(context, R.color.colorAccent)
        paint.textAlign=Paint.Align.CENTER
        paint.textSize=textSize
        paint.isFakeBoldText=true
        paint.strokeWidth=5f
        paint.isAntiAlias=true

        textPaint.color = ContextCompat.getColor(context,android.R.color.white)
        textPaint.textAlign=Paint.Align.CENTER
        textPaint.strokeWidth=10f
        textPaint.textSize=textSize
        textPaint.isAntiAlias=true
    }




    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.textSize=textSize
        paint.textSize=textSize


        canvas.translate((measuredWidth/2).toFloat(),(measuredHeight/6).toFloat())

        mx=(width/3).toFloat()

        my=(height/4).toFloat()

        canvas.translate(0f,-10f)
        canvas.drawText(teamsgroup,0f,0f,paint)
        canvas.translate(0f,10f)


        canvas.save()
        drawSchShape(canvas, team1Name,team2Name,datetextMatch1,matchResult1,isMatchPlayed1)
        canvas.restore()





    }



    private fun drawSchShape(canvas: Canvas
                             , team1Name: String, team2Name: String
                             , dateText1: String
                             , matchResult: String, isMatchPlayed:Boolean) {

        canvas.translate(0f,0f)

        canvas.translate(0f,my)

        canvas.drawLine(-mx-2.8f,0f,mx+2.8f,0f,paint)   //horizontal line

        canvas.translate(0f,-my)

        canvas.translate(-mx ,my)


        canvas.drawLine(0f,0f,0f,my,paint)    //vertical left line





        canvas.translate(mx ,-my)

        canvas.translate(mx,my)

            canvas.drawLine(0f,0f,0f,my,paint) //vertical right line



                canvas.save()
                canvas.translate((mx/2),my/2)
                canvas.translate(-mx-mx,0f)
                canvas.translate(-mx/4,my/4)

        if (team1Name.length < 20)
                canvas.drawText(team1Name,-mx/4,my ,textPaint) //left team text name
        else {
            drawLayeredText(team1Name, canvas, -mx/4, my , textPaint)
        }

                canvas.restore()

                canvas.save()
                canvas.translate(-mx/2,my/2)
                canvas.translate(mx/4,my/4)

        if (team2Name.length < 20)
        canvas.drawText(team2Name,mx/4,my ,textPaint) //right team text name
        else
            drawLayeredText(team2Name, canvas, mx/4, my , textPaint)

                canvas.restore()
                canvas.save()
                canvas.translate(-mx ,my/4)
                canvas.translate(0f ,my/2)
                when {
                    !isMatchPlayed -> {

                        canvas.drawText(dateText1, 0f, my , textPaint) //match time text

                    }
                    else -> //canvas?.translate(0f, 30f)
                        canvas.drawText(matchResult, 0f, my  , textPaint) //match result text
                }
                canvas.restore()
        }

    private fun drawLayeredText(
        team1Name: String,
        canvas: Canvas,
        mx: Float,
        my: Float,
        textPaint: Paint
    ) {
        val layers = team1Name.split(" ")
        var separator = 0
//        if (layers.size <= 3)
//        for (t in layers) {
//            separator += 2
//            canvas.drawText(t, mx, (my/4) * separator, textPaint)
//        }
//        else

        if (layers.first().length < 10 ) {
            canvas.drawText(layers.first() + " " + layers[1], mx, my, textPaint)
            canvas.drawText(layers.last(), mx, my + textSize, textPaint)
        }
        else{
            canvas.drawText(layers.first(), mx, my, textPaint)
            canvas.drawText(layers[1], mx, my + textSize, textPaint)
            canvas.drawText(layers.last(), mx, my + textSize * 2, textPaint)
        }

    }
}

