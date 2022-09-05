package com.yogandrn.foodmarket.kotlin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.yogandrn.foodmarket.kotlin.R
import kotlinx.android.synthetic.main.layout_toolbar.*


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request", 0);
        if (pageRequest == 2) {
            toolbarSignup()
            val navOption = NavOptions.Builder().setPopUpTo(R.id.fragmentSignin, true).build()

            Navigation.findNavController(findViewById(R.id.authHostFragment)).navigate(R.id.action_signup, null, navOption)
        }
    }

    fun toolbarSignup() {
        toolbar.title = resources.getString(R.string.title_signup)
        toolbar.subtitle = resources.getString(R.string.subtitle_signup)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow_back_ios)
        toolbar.setNavigationOnClickListener{onBackPressed()}
    }

    fun toolbarSignupAddress() {
        toolbar.title = resources.getString(R.string.title_signup_address)
        toolbar.subtitle = resources.getString(R.string.subtitle_signup_address)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow_back_ios)
        toolbar.setNavigationOnClickListener{onBackPressed()}
    }

    fun toolbarSignupSuccess() {
        toolbar.visibility = View.GONE
    }

}