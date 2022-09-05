package com.yogandrn.foodmarket.kotlin.ui.auth

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_signin.*


class SigninFragment : Fragment(), SignInContract.View{

    lateinit var presenter : SignInPresenter
    var progresDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignInPresenter(this)

        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initView()

        signin_to_signup_button.setOnClickListener{
            val signup = Intent(activity, AuthActivity::class.java);
            signup.putExtra("page_request", 2);
            startActivity(signup)
        }


        button_login.setOnClickListener {
            var email : String = signin_email.text.toString()
            var password : String = signin_pasword.text.toString()
            if (email.isNullOrEmpty()) {
                signin_email.setError("Email harus diisi!")
                signin_email.requestFocus()
            } else if (password.isNullOrEmpty()) {
                signin_pasword.setError("Password tidak boleh kosong!")
                signin_pasword.requestFocus()
            } else if (!email.contains("@") || !email.contains(".")) {
                signin_email.setError("Email tidak valid")
                signin_email.requestFocus()
            } else {
//                Toast.makeText(context, email + " | " + password, Toast.LENGTH_SHORT).show()
                    presenter.submitLogin(email, password)
//                presenter.submitLogin("budi.susanto@gmail.com", "password")
            }
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        progresDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progresDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }
    }

    override fun showLoading() {
        progresDialog?.show()
    }

    override fun dismissLoading() {
        progresDialog?.dismiss()
    }

}