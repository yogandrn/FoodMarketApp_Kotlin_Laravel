package com.yogandrn.foodmarket.kotlin.ui.profile.editaddress

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
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfileContract
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_edit_address.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class EditAddressFragment : Fragment(), ProfileContract.View {

    private lateinit var presenter : ProfilePresenter
    private var progressDialog : Dialog? = null
    private var data : User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var bundle : Bundle? = activity?.intent?.extras
        data = bundle?.get("data") as User

        presenter = ProfilePresenter(this)

        initView(data!!)

        button_edit_address.setOnClickListener {
            var address = edit_address.text.toString()
            var houseNumber = edit_house_number.text.toString()
            var city = edit_city.text.toString()
            if (address.isNullOrEmpty()) {
                edit_address.setError(resources.getString(R.string.address_empty))
                edit_address.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                edit_house_number.setError(resources.getString(R.string.house_empty))
                edit_house_number.requestFocus()
            }else if (city.isNullOrEmpty()) {
                edit_city.setError(resources.getString(R.string.city_empty))
                edit_city.requestFocus()
            } else {
                presenter.updateAddress(address, houseNumber, city)
            }
        }

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

        edit_address.setText(data.address)
        edit_house_number.setText(data.houseNumber)
        edit_city.setText(data.city)


    }


    override fun onProfileSuccess(user: User) {
        Toast.makeText(requireContext(), resources.getString(R.string.data_updated), Toast.LENGTH_SHORT).show()
        val gson = Gson()
        val json = gson.toJson(user)
        FoodMarket.getApp().setUser(json)

        activity?.finish()
    }

    override fun onProfileFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}