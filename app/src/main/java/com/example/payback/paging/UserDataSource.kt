package com.example.payback.paging

import android.content.Context
import androidx.paging.PageKeyedDataSource
import com.example.payback.models.HitModel
import com.example.payback.models.Hits
import com.example.payback.paging.Utility.isInternetAvailable
import com.example.payback.remote.APIInterface
import com.example.payback.remote.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource(private val context: Context, var id : String) : PageKeyedDataSource<Int, Hits>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Hits>) {
        if (context.isInternetAvailable()) {
            getUsers(id,callback)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Hits>) {
        if (context.isInternetAvailable()) {
            val nextPageNo = params.key + 1
            getMoreUsers(params.key, nextPageNo, callback)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Hits>) {
        if (context.isInternetAvailable()) {
            val previousPageNo = if (params.key > 1) params.key - 1 else 0
            getMoreUsers(params.key, previousPageNo, callback)
        }
    }

    private fun getUsers(id: String, callback: LoadInitialCallback<Int, Hits>) {

//        context.showProgressBar()
        val serviceBuilder = ServiceBuilder(context)
        val destinationService  = serviceBuilder.buildService(APIInterface::class.java)
        destinationService.getAffectedHitsList(id).enqueue(object : Callback<HitModel> {
            override fun onFailure(call: Call<HitModel>, t: Throwable) {
                Utility.hideProgressBar()
            }

            override fun onResponse(call: Call<HitModel>, response: Response<HitModel>) {
                Utility.hideProgressBar()
                val userResponse = response.body()
                val listUsers = userResponse?.hits
                listUsers?.let { callback.onResult(it, null, 2) }
            }

        })

    }

    private fun getMoreUsers(
        previousOrNextPageNo: Int,
        previousPageNo: Int,
        callback: LoadCallback<Int, Hits>
    ) {
        val serviceBuilder = ServiceBuilder(context)
        val destinationService  = serviceBuilder.buildService(APIInterface::class.java)
        destinationService.getAffectedHitsList(id).enqueue(object : Callback<HitModel> {
            override fun onFailure(call: Call<HitModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<HitModel>, response: Response<HitModel>) {
                val userResponse = response.body()
                val listUsers = userResponse?.hits
                listUsers?.let { callback.onResult(it, previousOrNextPageNo) }
            }

        })

    }

}