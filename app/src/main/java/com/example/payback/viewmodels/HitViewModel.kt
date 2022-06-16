package com.example.payback.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payback.models.hitmodel
import com.example.payback.remote.APIInterface
import com.example.payback.remote.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import android.graphics.Color;
import androidx.databinding.Bindable

class HitViewModel : ViewModel() {
    var recyclerlivedata : MutableLiveData<hitmodel> = MutableLiveData()
    lateinit var id :String
    fun GetListOservable (identify :  String) : LiveData<hitmodel>
    {
        id = identify
        return recyclerlivedata
    }


    fun MakeApiCall()
    {
        viewModelScope.launch (Dispatchers.IO) {
            val destinationService  = ServiceBuilder.buildService(APIInterface::class.java)
            val requestCall =destinationService.getAffectedHitsList(id)
            requestCall.enqueue(object : Callback<hitmodel> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: retrofit2.Call<hitmodel>, response: Response<hitmodel>) {
                    Log.d("Response", "onResponse: ${response.body()}")
                    if (response.isSuccessful){
                        recyclerlivedata.value = response.body()
                    }else{
                        Log.d("Response", "onResponse: ${response.body()}")
                    }
                }
                override fun onFailure(call: retrofit2.Call<hitmodel>, t: Throwable) {
                    Log.d("Response", "onResponse: $t")
                }
            })
        }

    }

}