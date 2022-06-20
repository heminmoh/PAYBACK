/**
 * getListObservable is a function in HitViewModel that take context and searched data
 * and return  LiveData<HitModel> object
 * 2022-06-18 18:00
 */

package com.example.payback.viewmodels

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.payback.models.HitModel
import com.example.payback.models.Hits
import com.example.payback.models.IHitRepository
import com.example.payback.remote.APIInterface
import com.example.payback.remote.ServiceBuilder
import com.example.payback.repositories.HitRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

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