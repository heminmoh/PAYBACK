package com.example.payback.remote

import com.example.payback.models.hitmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public  interface APIInterface {

    @GET("api/?key=28064425-bf298584527deb9f5f55f7fe7&q=search_id&image_type=photo&pretty=true")
    fun getAffectedHitsList(@Query("q") search_id : String ) : Call<hitmodel>

}