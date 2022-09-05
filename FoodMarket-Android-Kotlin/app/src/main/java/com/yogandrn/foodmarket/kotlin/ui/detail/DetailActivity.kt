package com.yogandrn.foodmarket.kotlin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.home.Data

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        var bundle : Bundle? = intent.extras
//        var data : Data = bundle?.get("data") as Data
//        Log.v("data", data.description.toString())

        intent.extras?.let {
            var navController = Navigation.findNavController(findViewById(R.id.detail_host_fragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable)
            var data : Data = bundle.get("data") as Data
            navController.setGraph(navController.graph, bundle)
        }
    }


//    fun toolbarPayment() {
//        toolbar.title = resources.getString(R.string.title_payment)
//        toolbar.subtitle = resources.getString(R.string.subtitle_payment)
//        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow_back_ios, null)
//        toolbar.setNavigationOnClickListener{ onBackPressed() }
//    }
//
//    fun toolbarDetail() {
//        toolbar.visibility = View.GONE
//    }


}