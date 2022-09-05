package com.yogandrn.foodmarket.kotlin.ui.profile.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.login.User

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        intent.extras?.let {
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable)
            var navController = Navigation.findNavController(findViewById(R.id.user_profile_nav_host))
            navController.setGraph(navController.graph, bundle)
        }


    }
}