package com.example.payback.models

import android.content.Context

interface ICheckInternetConnection {
    fun checkForInternet(context: Context): Boolean
}