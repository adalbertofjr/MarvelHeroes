package br.com.adalbertofjr.marvelheroes.util

import android.net.ConnectivityManager


class NetworkUtil {
    companion object {
        fun isConnected(connectivityManager: ConnectivityManager): Boolean {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}