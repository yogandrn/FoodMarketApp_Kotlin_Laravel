package com.yogandrn.foodmarket.kotlin.ui.orderdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.navigation.Navigation
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data

class OrderDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        intent.extras?.let {
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable)
            var navController = Navigation.findNavController(findViewById(R.id.detail_order_host_fragment))
            navController.setGraph(navController.graph, bundle)
        }
    }
}