package com.yogandrn.foodmarket.kotlin.ui.profile.editprofile

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfileContract
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfilePresenter

class UserProfileFragment : Fragment(){

    private lateinit var presenter : ProfilePresenter
    private var progressDialog : Dialog? = null
    private var data : User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var bundle : Bundle? = activity?.intent?.extras
        data = bundle?.get("data") as User

        initView(data!!)
    }

    fun initView(data : User) {
        //alert loading
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }

    }

}