package com.gateway.marvel.data.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService

@Suppress("DEPRECATION")
fun checkIfOnline(context: Context): Boolean {


    val cm = getSystemService(context, ConnectivityManager::class.java)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities = cm?.getNetworkCapabilities(cm.activeNetwork) ?: return false
        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    } else {
        cm?.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}





