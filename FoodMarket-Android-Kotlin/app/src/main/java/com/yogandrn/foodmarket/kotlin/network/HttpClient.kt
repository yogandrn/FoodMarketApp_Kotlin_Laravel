package com.yogandrn.foodmarket.kotlin.network

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.utils.Helpers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client:Retrofit? = null
    private var endpoint:EndPoint? = null
    private var baseUrl:String = "http://sipaling-ngoding.my.id/foodmarket/api/"
//    private var baseUrl:String = "http://kopikode.oool.ooo/foodmarket/api/"
//    private var baseUrl:String = "http://192.168.1.3:8080/food_market/public/api/"

    companion object {
        private val mInstance:HttpClient = HttpClient()
        fun getInstance():HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getAPI():EndPoint? {
        if (endpoint == null) {
            endpoint= client!!.create(EndPoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = FoodMarket.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token:String?) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level =  HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(FoodMarket.getApp()))
        }

        if (token != null) {
            builder.addInterceptor(getInterceptorWithHeader("Authorization", "Bearer ${token}"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers().getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        endpoint = null

        Log.v("token", token.toString())
    }

    private fun getInterceptorWithHeader(headerName:String, headerValue:String) : Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getInterceptorWithHeader(header)
    }

    private fun getInterceptorWithHeader(header: Map<String, String>) : Interceptor {
        return Interceptor {
            val original = it.request()
            val builder = original.newBuilder()
            for ((key, value) in header){
                builder.addHeader(key, value)
            }
            builder.method(original.method(), original.body())
            it.proceed(builder.build())
        }
    }
}