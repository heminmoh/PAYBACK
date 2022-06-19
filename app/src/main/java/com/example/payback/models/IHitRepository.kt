package com.example.payback.models

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface IHitRepository {
    var recyclerLiveData: MutableLiveData<HitModel>
    fun makeApiCall(context: Context, id : String  ) : MutableLiveData<HitModel>
}