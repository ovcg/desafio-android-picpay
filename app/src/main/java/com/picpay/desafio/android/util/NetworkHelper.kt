package com.picpay.desafio.android.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkHelper(val context: Context) {

    fun isConnected(): Boolean {
        return networkCapabilities()?.let { capabilities ->
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } ?: false
    }

    private fun getConnectivityManager(): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private fun networkCapabilities(): NetworkCapabilities? {
        val connectivityManager = getConnectivityManager()
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    }
}