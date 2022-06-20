/**
 * IHitRepository is an interface for HitRepositoryImp class
 * class call Api by coroutine method
 *  2022-06-18  12:37
 */

package com.example.payback.models

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface IHitRepository {
    var recyclerLiveData: MutableLiveData<HitModel>
    fun makeApiCall(context: Context, id : String  ) : MutableLiveData<HitModel>
}