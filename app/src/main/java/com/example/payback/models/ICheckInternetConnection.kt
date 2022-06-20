
/**
 * function of checkForInternet in this class check Internet connection
 * context is the enter param  and return a Boolean value which shows Internet Status
 *  2022-06-17  09:37
 */

package com.example.payback.models

import android.content.Context

interface ICheckInternetConnection {
    fun checkForInternet(context: Context): Boolean
}