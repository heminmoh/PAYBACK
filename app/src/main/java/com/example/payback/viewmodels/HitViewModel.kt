package com.example.payback.viewmodels

import android.content.Context
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
import android.widget.Toast
import androidx.databinding.Bindable
import com.example.payback.R
import javax.inject.Inject

class HitViewModel : ViewModel() {
    var recyclerlivedata : MutableLiveData<hitmodel> = MutableLiveData()
    lateinit var id :String
    fun getListObservable (identify :  String) : LiveData<hitmodel>
    {
        id = identify
        return recyclerlivedata
    }


    fun makeApiCall(context: Context)
    {
        viewModelScope.launch (Dispatchers.IO) {
            val servicebuilder: ServiceBuilder = ServiceBuilder(context)
//            servicebuilder.init(context)

            val destinationService  = servicebuilder.buildService(APIInterface::class.java)
            val requestCall =destinationService.getAffectedHitsList(id)
            requestCall.enqueue(object : Callback<hitmodel> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: retrofit2.Call<hitmodel>, response: Response<hitmodel>) {
                    if (response.isSuccessful){
                        recyclerlivedata.value = response.body()
                    }else{
                        Log.d("Response", "onResponse: ${response.body()}")
                    }
                }
                override fun onFailure(call: retrofit2.Call<hitmodel>, t: Throwable) {
                }
            })
        }

    }

}