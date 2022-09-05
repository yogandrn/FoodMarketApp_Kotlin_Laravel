package com.yogandrn.foodmarket.kotlin

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.yogandrn.foodmarket.kotlin.network.HttpClient

class FoodMarket : MultiDexApplication() {

    var imageURL : String = "http://sipaling-ngoding.my.id/foodmarket/laravel/storage/app/public/"
//    var imageURL : String = "http://kopikode.oool.ooo/foodmarket/laravel/storage/app/public/"

    companion object {
        lateinit var instance : FoodMarket
        fun getApp() : FoodMarket {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    fun getPreferences() : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token:String) {
        getPreferences().edit().putString("USER_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPreferences().getString("USER_TOKEN", null)
    }

    fun setUser(user:String) {
        getPreferences().edit().putString("USER_DATA", user).apply()
    }

    fun getUser() : String? {
        return getPreferences().getString("USER_DATA", null)
    }

    fun deleteSession() {
        getPreferences().edit().clear().apply()
    }

}