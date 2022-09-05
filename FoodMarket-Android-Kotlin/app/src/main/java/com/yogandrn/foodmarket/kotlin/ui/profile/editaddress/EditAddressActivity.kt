package com.yogandrn.foodmarket.kotlin.ui.profile.editaddress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.Navigation
import com.yogandrn.foodmarket.kotlin.R

class EditAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        intent.extras?.let {
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable)
            var navController = Navigation.findNavController(findViewById(R.id.user_address_nav_host))
            navController.setGraph(navController.graph, bundle)
        }
    }
}