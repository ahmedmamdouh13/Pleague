package com.ahmedmamdouh13.theleague.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener.Companion.deviceHeight
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener.Companion.deviceWidth
import kotlinx.android.synthetic.main.favorite_screen.view.*

class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favorite_screen, null, false)

        view.favorite_screen_container_favoritefragment.layoutParams = ViewGroup.LayoutParams(deviceWidth,deviceHeight)



        return view
    }

}