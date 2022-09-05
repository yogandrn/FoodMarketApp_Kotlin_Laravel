package com.yogandrn.foodmarket.kotlin.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.yogandrn.foodmarket.kotlin.R
import kotlinx.android.synthetic.main.dialog_exit.view.*

class MainActivity : AppCompatActivity() {

    private var backPressedTime = 0L;
    private lateinit var navView: BottomNavigationView

//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_order, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
//
//    override fun onBackPressed() {
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            super.onBackPressed()
//        } else {
//            Toast.makeText(applicationContext, resources.getString(R.string.tap_again), Toast.LENGTH_SHORT).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//
//    }

    override fun onBackPressed() {
        if (navView.selectedItemId == R.id.navigation_home) {
            // inflate dialog with customview
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exit, null)
            // alert dialog builder
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView).setCancelable(true)
            // show alert
            val mAlertDialog = mBuilder.show()
            // set button action
            mDialogView.button_exit.setOnClickListener {

                super.onBackPressed()
                mAlertDialog.dismiss()
            }
            mDialogView.button_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_order, R.id.navigation_profile
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        NavigationUI.setupWithNavController(navView, navController)
//        navView.setupWithNavController(navController)
    }
}