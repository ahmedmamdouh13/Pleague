package com.ahmedmamdouh13.theleague.ui.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ahmedmamdouh13.cornertoview.Controller
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.presentaion.MainViewModel
import com.ahmedmamdouh13.theleague.ui.application.LeagueApplication
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity()  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
     init()


        val first = MatchesFragment()
        val second = FavoriteFragment()
        val controller = Controller(first, second, supportFragmentManager)

        controller.setFirstCollapseIcon(R.drawable.ic_list_black_24dp)
        controller.setSecondCollapseIcon(R.drawable.ic_favorite_black_24dp)

        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container_mainactivity,
                    controller
                ).commit()



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

}

