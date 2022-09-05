package com.yogandrn.foodmarket.kotlin.ui.auth

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.request.RegisterRequest
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup_address.*

class SignupAddressFragment : Fragment(), SignUpContract.View {

    private lateinit var data : RegisterRequest
    lateinit var presenter : SignUpPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)

        initView()

        data = arguments?.getParcelable<RegisterRequest>("data")!!

        signup_button.setOnClickListener{
            var phone = signup_phone.text.toString()
            var address = signup_address.text.toString()
            var house_number = signup_house_number.text.toString()
            var city = signup_city.text.toString()

            data.let {
                it.phoneNumber = phone
                it.address =address
                it.houseNumber =house_number
                it.city =city
            }

//            presenter.uploadPhotoProfile(data.filePath!!, it)

            if (phone.isNullOrEmpty()) {
                signup_phone.setError(resources.getString(R.string.phone_empty))
                signup_phone.requestFocus()
            } else if (phone.length < 10 || phone.length > 15) {
                signup_phone.setError(resources.getString(R.string.invalid_phone))
                signup_phone.requestFocus()
            } else if (address.isNullOrEmpty()) {
                signup_address.setError(resources.getString(R.string.address_empty))
                signup_address.requestFocus()
            } else if (house_number.isNullOrEmpty()) {
                signup_house_number.setError(resources.getString(R.string.house_empty))
                signup_house_number.requestFocus()
            } else if (city.isNullOrEmpty()) {
                signup_city.setError(resources.getString(R.string.city_empty))
                signup_city.requestFocus()
            } else {
                presenter.submitRegister(data, it)

            }
        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view).navigate(R.id.action_signup_success, null)
            (activity as AuthActivity).toolbarSignupSuccess()
        } else {
            presenter.uploadPhotoProfile(data.filePath!!, view)
        }


    }

    override fun onUploadPhotoSuccess(user: User,view: View) {
        val gson = Gson()
        val json = gson.toJson(user)
        FoodMarket.getApp().setUser(json)
        Navigation.findNavController(view).navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignupSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }
    }

}