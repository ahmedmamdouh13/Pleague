package com.ahmedmamdouh13.theleague.ui.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by ahmedmamdouh13 on 16/11/17.
 */

object InternetConnection {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}
