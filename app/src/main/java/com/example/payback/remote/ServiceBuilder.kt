package com.example.payback.remote



import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.payback.utilities.CheckInternetConnection
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
public class ServiceBuilder (context: Context) {



    private val cacheSize = (10 * 1024 * 1024).toLong()
    private val myCache = Cache(context.cacheDir, cacheSize)
    private val checkInternetConnection= CheckInternetConnection()
    private  val URL ="https://pixabay.com/api/"
    private val okHttp =OkHttpClient.Builder().cache(myCache).addInterceptor{
        chain ->  var request = chain.request()
                 request = if (checkInternetConnection.checkForInternet(context)!!)
            request.newBuilder().header("Cache-Control", "public, max-age=" + 4).build()
        else
            request.newBuilder().header(
                "Cache-Control",
                "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
            ).build()
        chain.proceed(request)
    }

    private val builder =Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

   public fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }


}