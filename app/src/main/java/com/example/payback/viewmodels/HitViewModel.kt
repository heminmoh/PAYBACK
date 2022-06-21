/**
 * getListObservable is a function in HitViewModel that take context and searched data
 * and return  LiveData<HitModel> object
 * 2022-06-18 18:00
 */

package com.example.payback.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.payback.models.HitModel
import com.example.payback.repositories.HitRepositoryImp

class HitViewModel() : ViewModel() {
    private var recyclerLiveData : MutableLiveData< HitModel> = MutableLiveData()
    lateinit var id :String



    fun getListObservable (context: Context, identify: String) : LiveData<HitModel>
    {
        id = identify
        recyclerLiveData = HitRepositoryImp().makeApiCall(context, id)
        return recyclerLiveData
    }


}