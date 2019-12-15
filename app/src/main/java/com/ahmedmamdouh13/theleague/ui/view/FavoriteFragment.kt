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
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.adapter.MatchesFavoriteRecyclerAdapter
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener.Companion.deviceHeight
import com.ahmedmamdouh13.theleague.ui.custom.ScreenTouchListener.Companion.deviceWidth
import kotlinx.android.synthetic.main.favorite_screen.*
import kotlinx.android.synthetic.main.favorite_screen.view.*
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        val view = inflater.inflate(R.layout.favorite_screen, null, false)

        view.favorite_screen_container_favoritefragment.layoutParams = ViewGroup.LayoutParams(deviceWidth,deviceHeight)

        viewModel.checkToggleListener.observe(viewLifecycleOwner,viewModel.checkObserver)
        viewModel.unCheckToggleListener.observe(viewLifecycleOwner,viewModel.unCheckObserver)


        val adapter = MatchesFavoriteRecyclerAdapter().apply {
            setFavoriteListeners(viewModel.checkToggleListener, viewModel.unCheckToggleListener)
            viewModel.matchesFavoriteLiveData.observe(viewLifecycleOwner, Observer {
                list = it
                if (!it.isNullOrEmpty()) {
                    placeholder_favoritescreen.visibility = View.GONE
                }
                    else
                    placeholder_favoritescreen.visibility = View.VISIBLE
            })
        }
        view.matches_recyclerview_favoritescreen.itemAnimator = DefaultItemAnimator()
        view.matches_recyclerview_favoritescreen.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        view.matches_recyclerview_favoritescreen.adapter = adapter




        return view
    }


    private fun init() {
        (activity?.application as LeagueApplication)
            .appInjection()
            .inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]

    }

}