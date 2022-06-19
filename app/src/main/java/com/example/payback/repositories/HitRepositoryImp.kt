package com.example.payback.repositories

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.payback.models.HitModel
import com.example.payback.remote.APIInterface
import com.example.payback.remote.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class HitRepositoryImp {
    var recyclerLiveData : MutableLiveData<HitModel> = MutableLiveData()
    fun makeApiCall(context: Context, id : String  ) : MutableLiveData<HitModel>
    {
        GlobalScope.launch (Dispatchers.IO) {
            val serviceBuilder = ServiceBuilder(context)

            val destinationService  = serviceBuilder.buildService(APIInterface::class.java)
            val requestCall =destinationService.getAffectedHitsList(id)
            requestCall.enqueue(object : Callback<HitModel> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: retrofit2.Call<HitModel>, response: Response<HitModel>) {
                    if (response.isSuccessful){
                        recyclerLiveData.value = response.body()
                    }else{
                        Log.d("Response", "onResponse: ${response.body()}")
                    }
                }
                override fun onFailure(call: retrofit2.Call<HitModel>, t: Throwable) {
                }
            })
        }
       return recyclerLiveData
    }

}