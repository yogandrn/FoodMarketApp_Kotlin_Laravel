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
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfileContract
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class EditProfileFragment : Fragment(), ProfileContract.View {

    private lateinit var presenter : ProfilePresenter
    private var progressDialog : Dialog? = null
    private var data : User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = ProfilePresenter(this)
        var bundle : Bundle? = activity?.intent?.extras
        data = bundle?.get("data") as User


        initView(data!!)

        button_edit_profile.setOnClickListener {
            var name = edit_name.text.toString()
            var email = edit_email.text.toString()
            var phone = edit_phone.text.toString()

            if (name.isNullOrEmpty()) {
                edit_name.setError(resources.getString(R.string.name_empty))
                edit_name.requestFocus()
            } else if (email.isNullOrEmpty()) {
                edit_email.setError(resources.getString(R.string.email_empty))
                edit_email.requestFocus()
            }else if (phone.isNullOrEmpty()) {
                edit_phone.setError(resources.getString(R.string.phone_empty))
                edit_phone.requestFocus()
            } else if (!email.contains("@") || !email.contains(".")) {
                edit_email.setError(resources.getString(R.string.invalid_email))
                edit_email.requestFocus()
            }else if (phone.length < 10 || phone.length > 15) {
                edit_phone.setError(resources.getString(R.string.invalid_phone))
                edit_phone.requestFocus()
            } else {
                presenter.updateUser(name, email, phone)
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

        edit_name.setText(data.name)
        edit_email.setText(data.email)
        edit_phone.setText(data.phoneNumber)

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